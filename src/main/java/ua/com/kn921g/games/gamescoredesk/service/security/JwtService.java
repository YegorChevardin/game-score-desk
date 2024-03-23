package ua.com.kn921g.games.gamescoredesk.service.security;

import ua.com.kn921g.games.gamescoredesk.models.security.JwtUserDetails;

import java.util.List;

public interface JwtService {
    String createAccessToken(String userId, List<String> permissions);

    boolean validateAccessToken(String fullAccessToken);

    JwtUserDetails parseAccessToken(String fullAccessToken);
}
