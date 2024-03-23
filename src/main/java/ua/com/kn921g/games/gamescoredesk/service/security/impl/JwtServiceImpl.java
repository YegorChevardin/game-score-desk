package ua.com.kn921g.games.gamescoredesk.service.security.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.kn921g.games.gamescoredesk.common.GameBoardExceptionMessages;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataNotValidException;
import ua.com.kn921g.games.gamescoredesk.models.security.JwtUserDetails;
import ua.com.kn921g.games.gamescoredesk.service.security.AuthoritiesService;
import ua.com.kn921g.games.gamescoredesk.service.security.JwtService;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static ua.com.kn921g.games.gamescoredesk.common.GameScoreBoardConstants.ACCESS_TOKEN_EXPIRATION;
import static ua.com.kn921g.games.gamescoredesk.common.GameScoreBoardConstants.ACCESS_TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
  private final AuthoritiesService authoritiesService;

  @Value("${application.secretKey}")
  private String secretKey;

  @Override
  public String createAccessToken(String userId, List<String> permissions) {
    Date expiration = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRATION);
    return Jwts.builder()
        .claims()
        .subject(userId)
        .add("permissions", permissions)
        .and()
        .expiration(expiration)
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
        .compact();
  }

  @Override
  public boolean validateAccessToken(String fullAccessToken) {
    return isAccessTokenValid(fullAccessToken);
  }

  private boolean isAccessTokenValid(String fullAccessToken) {
    if (fullAccessToken == null || !fullAccessToken.startsWith(ACCESS_TOKEN_PREFIX)) {
      return false;
    }
    try {
      String token = fullAccessToken.substring(ACCESS_TOKEN_PREFIX.length());
      return isTokenExpired(token);
    } catch (JwtException e) {
      log.error(e.getMessage());
      return false;
    }
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
    Claims claims = getAllClaimsFromToken(token);
    return claimResolver.apply(claims);
  }

  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token) {
    Date expirationDate = getExpirationDateFromToken(token);
    return !expirationDate.before(new Date());
  }

  @Override
  public JwtUserDetails parseAccessToken(String fullAccessToken) {
    if (!isAccessTokenValid(fullAccessToken)) {
      throw new DataNotValidException(GameBoardExceptionMessages.TOKEN_IS_NOT_VALID.getMessage());
    }
    String token = fullAccessToken.substring(ACCESS_TOKEN_PREFIX.length());
    String userId = getClaimFromToken(token, Claims::getSubject);
    List<String> permissions =
        getClaimFromToken(
            token,
            claims -> {
              try {
                return claims.get("permissions", List.class);
              } catch (ClassCastException e) {
                throw new DataNotValidException(
                    GameBoardExceptionMessages.TOKEN_IS_NOT_VALID.getMessage());
              }
            });
    return new JwtUserDetails(
        userId, authoritiesService.fetchAuthoritiesFromPermissions(permissions));
  }
}
