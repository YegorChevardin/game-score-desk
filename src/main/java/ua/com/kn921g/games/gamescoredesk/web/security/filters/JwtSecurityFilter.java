package ua.com.kn921g.games.gamescoredesk.web.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.GameScoreBoardBaseException;
import ua.com.kn921g.games.gamescoredesk.service.security.impl.JwtUserDetailsService;

import java.io.IOException;

import static ua.com.kn921g.games.gamescoredesk.common.GameScoreBoardConstants.ACCESS_TOKEN_HEADER_NAME;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {
  private final HandlerExceptionResolver resolver;
  private final JwtUserDetailsService userDetailsService;

  public JwtSecurityFilter(
      @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver,
      JwtUserDetailsService userDetailsService) {
    this.resolver = resolver;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String fullToken = request.getHeader(ACCESS_TOKEN_HEADER_NAME);
      if (fullToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(fullToken);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, fullToken, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }

      filterChain.doFilter(request, response);
    } catch (GameScoreBoardBaseException e) {
      resolver.resolveException(request, response, null, e);
    }
  }
}
