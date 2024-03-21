package ua.com.kn921g.games.gamescoredesk.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kn921g.games.gamescoredesk.service.AuthenticationService;
import ua.com.kn921g.games.generated.dto.LoginRequestDto;
import ua.com.kn921g.games.generated.dto.LoginResponseDto;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
        return null;
    }
}
