package ua.com.kn921g.games.gamescoredesk.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserRegisterRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserResponseDto;

public interface ProfileService {
    @PreAuthorize("hasRole('ROLE_DELETE_OWN_PROFILE')")
    void deleteUser(String password);

    @PreAuthorize("hasRole('ROLE_READ_OWN_PROFILE')")
    UserFullResponseDto findUserInformation();

    @PreAuthorize("hasRole('ROLE_UPDATE_OWN_PROFILE')")
    UserResponseDto updateUser(String oldPassword, UserRegisterRequestDto updateDto);
}
