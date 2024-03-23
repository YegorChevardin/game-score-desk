package ua.com.kn921g.games.gamescoredesk.web.deligates.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.com.kn921g.games.gamescoredesk.generated.api.AuthenticationApiDelegate;
import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserRegisterRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserResponseDto;
import ua.com.kn921g.games.gamescoredesk.service.AuthenticationService;

@Component
@RequiredArgsConstructor
public class AuthenticationApiDelegateImpl implements AuthenticationApiDelegate {
  private final AuthenticationService authenticationService;

  @Override
  public ResponseEntity<LoginResponseDto> userLogin(LoginRequestDto loginRequestDto) {
    return ResponseEntity.ok(authenticationService.loginUser(loginRequestDto));
  }

  @Override
  public ResponseEntity<UserResponseDto> userRegister(
      UserRegisterRequestDto userRegisterRequestDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authenticationService.registerUser(userRegisterRequestDto));
  }
}
