package ua.com.kn921g.games.gamescoredesk.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.kn921g.games.gamescoredesk.models.security.RolesAndPermissions;
import ua.com.kn921g.games.gamescoredesk.service.security.PermissionLoaderService;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PermissionsConfig {
  private final PermissionLoaderService permissionLoaderService;

  @Bean
  public RolesAndPermissions rolesAndPermissions() {
    RolesAndPermissions rolesAndPermissions =
        permissionLoaderService.loadRolesAndPermissionsFromResources();
    log.info(
        String.format("Roles and permissions are initialized: %s", rolesAndPermissions.toString()));
    return rolesAndPermissions;
  }
}
