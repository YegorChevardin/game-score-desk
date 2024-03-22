package ua.com.kn921g.games.gamescoredesk.service;

import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);
}
