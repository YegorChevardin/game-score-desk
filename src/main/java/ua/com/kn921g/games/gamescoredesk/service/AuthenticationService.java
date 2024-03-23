package ua.com.kn921g.games.gamescoredesk.service;

import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginResponseDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserRegisterRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserResponseDto;

public interface AuthenticationService {
    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);

    UserResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);
}
