package ua.com.kn921g.games.gamescoredesk.web.deligates.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.com.kn921g.games.gamescoredesk.generated.api.ProfileApiDelegate;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserRegisterRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserResponseDto;
import ua.com.kn921g.games.gamescoredesk.service.ProfileService;

@Component
@RequiredArgsConstructor
public class ProfileApiDelegateImpl implements ProfileApiDelegate {
  private final ProfileService profileService;

  @Override
  public ResponseEntity<Void> userDelete(String password) {
    profileService.deleteUser(password);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<UserFullResponseDto> userProfile() {
    return ResponseEntity.ok(profileService.findUserInformation());
  }

  @Override
  public ResponseEntity<UserResponseDto> userUpdate(
      String oldPassword, UserRegisterRequestDto userRegisterRequestDto) {
    return ResponseEntity.ok(profileService.updateUser(oldPassword, userRegisterRequestDto));
  }
}
