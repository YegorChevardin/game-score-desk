package ua.com.kn921g.games.gamescoredesk.service;

import ua.com.kn921g.games.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.generated.dto.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);
}
