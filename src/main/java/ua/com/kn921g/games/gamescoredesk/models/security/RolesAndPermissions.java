package ua.com.kn921g.games.gamescoredesk.models.security;

import lombok.Builder;

import java.util.List;

@Builder
public record RolesAndPermissions(List<String> user) {}
