package ua.com.kn921g.games.gamescoredesk.web.deligates.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.com.kn921g.games.gamescoredesk.service.AuthenticationService;
import ua.com.kn921g.games.generated.api.AuthenticationApiDelegate;
import ua.com.kn921g.games.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.generated.dto.LoginResponseDto;

@Component
@RequiredArgsConstructor
public class AuthenticationApiDelegateImpl implements AuthenticationApiDelegate {
  private final AuthenticationService authenticationService;

  @Override
  public ResponseEntity<LoginResponseDto> userLogin(LoginRequestDto loginRequestDto) {
    return ResponseEntity.ok(authenticationService.loginUser(loginRequestDto));
  }
}
