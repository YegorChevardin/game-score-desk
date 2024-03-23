package ua.com.kn921g.games.gamescoredesk.service.security;

import org.springframework.security.core.GrantedAuthority;
import ua.com.kn921g.games.gamescoredesk.models.security.Role;

import java.util.List;

public interface AuthoritiesService {
    List<GrantedAuthority> fetchAuthoritiesFromPermissions(List<String> permissions);

    List<String> fetchPermissionsFromRole(Role role);

    String receiveCurrentUserId();
}
