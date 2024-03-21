package ua.com.kn921g.games.gamescoredesk.service.security.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.kn921g.games.gamescoredesk.common.GameBoardExceptionMessages;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.Unauthorized401Exception;
import ua.com.kn921g.games.gamescoredesk.common.exceptions.impl.DataNotValidException;
import ua.com.kn921g.games.gamescoredesk.models.security.Role;
import ua.com.kn921g.games.gamescoredesk.models.security.RolesAndPermissions;
import ua.com.kn921g.games.gamescoredesk.service.security.AuthoritiesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {
  private static final String AUTHORITY_PREFIX = "ROLE_";

  private final RolesAndPermissions rolesAndPermissions;

  @Override
  public List<GrantedAuthority> fetchAuthoritiesFromPermissions(List<String> permissions) {
    return new ArrayList<>(
        permissions.stream()
            .map(element -> new SimpleGrantedAuthority(AUTHORITY_PREFIX + element))
            .toList());
  }

  @Override
  public List<String> fetchPermissionsFromRole(Role role) {
    if (Objects.requireNonNull(role) != Role.USER) {
      throw new DataNotValidException(GameBoardExceptionMessages.ROLE_NOT_FOUND.getMessage());
    }
    return rolesAndPermissions.user();
  }

  @Override
  public String receiveCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!isUserAuthenticated(authentication)) {
      throw new Unauthorized401Exception(
          GameBoardExceptionMessages.USER_NOT_AUTHORIZED.getMessage());
    }
    return authentication.getName();
  }

  private boolean isUserAuthenticated(Authentication authentication) {
    if (authentication == null
        || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
      return false;
    }
    return authentication.isAuthenticated();
  }
}
