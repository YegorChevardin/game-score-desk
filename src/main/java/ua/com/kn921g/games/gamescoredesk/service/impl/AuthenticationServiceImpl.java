package ua.com.kn921g.games.gamescoredesk.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.kn921g.games.gamescoredesk.common.GameBoardExceptionMessages;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.Unauthorized401Exception;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataNotFoundException;
import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginResponseDto;
import ua.com.kn921g.games.gamescoredesk.models.User;
import ua.com.kn921g.games.gamescoredesk.repositories.UserRepository;
import ua.com.kn921g.games.gamescoredesk.service.AuthenticationService;
import ua.com.kn921g.games.gamescoredesk.service.security.AuthoritiesService;
import ua.com.kn921g.games.gamescoredesk.service.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final AuthoritiesService authoritiesService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
    User user =
        userRepository
            .findByUsername(loginRequestDto.username())
            .orElseThrow(
                () ->
                    new DataNotFoundException(
                        GameBoardExceptionMessages.USER_NOT_FOUND.getMessage()));

    if (!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
      throw new Unauthorized401Exception(
          GameBoardExceptionMessages.CREDENTIALS_NOT_VALID.getMessage());
    }

    return LoginResponseDto.builder()
        .accessToken(
            jwtService.createAccessToken(
                user.getId().toString(),
                authoritiesService.fetchPermissionsFromRole(user.getRole())))
        .build();
  }
}
