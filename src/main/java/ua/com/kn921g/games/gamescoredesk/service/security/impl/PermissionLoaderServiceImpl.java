package ua.com.kn921g.games.gamescoredesk.service.security.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ua.com.kn921g.games.gamescoredesk.models.security.RolesAndPermissions;
import ua.com.kn921g.games.gamescoredesk.service.security.PermissionLoaderService;

import static ua.com.kn921g.games.gamescoredesk.common.GameScoreBoardConstants.PERMISSIONS_FILE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionLoaderServiceImpl implements PermissionLoaderService {
  private final ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public RolesAndPermissions loadRolesAndPermissionsFromResources() {
    ClassPathResource classPathResource = new ClassPathResource(PERMISSIONS_FILE);

    return objectMapper.readValue(classPathResource.getInputStream(), RolesAndPermissions.class);
  }
}
