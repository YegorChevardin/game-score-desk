package ua.com.kn921g.games.gamescoredesk.service.security;

import ua.com.kn921g.games.gamescoredesk.models.security.RolesAndPermissions;

public interface PermissionLoaderService {
    RolesAndPermissions loadRolesAndPermissionsFromResources();
}
