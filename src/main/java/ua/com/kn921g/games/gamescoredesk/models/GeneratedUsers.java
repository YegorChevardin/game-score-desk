package ua.com.kn921g.games.gamescoredesk.models;

import lombok.Builder;
import ua.com.kn921g.games.gamescoredesk.models.entities.User;

import java.util.List;

@Builder
public record GeneratedUsers(
        List<User> generatedUsers
) {}
