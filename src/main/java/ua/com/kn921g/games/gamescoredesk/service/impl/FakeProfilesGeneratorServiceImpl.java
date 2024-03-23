package ua.com.kn921g.games.gamescoredesk.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kn921g.games.gamescoredesk.models.GeneratedUsers;
import ua.com.kn921g.games.gamescoredesk.models.entities.User;
import ua.com.kn921g.games.gamescoredesk.repositories.UserRepository;
import ua.com.kn921g.games.gamescoredesk.service.FakeProfilesGeneratorService;

import java.util.List;

import static ua.com.kn921g.games.gamescoredesk.common.GameScoreBoardConstants.FAKE_USERS_FILE;

@Service
@RequiredArgsConstructor
public class FakeProfilesGeneratorServiceImpl implements FakeProfilesGeneratorService {
  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;

  @Override
  @Transactional
  public GeneratedUsers loadGeneratedUsers() {
    GeneratedUsers generatedUsers = loafFromResources();

    List<User> userEntities = generatedUsers.generatedUsers();
    userEntities.stream()
        .filter(user -> !userRepository.existsByUsername(user.getUsername()))
        .forEach(userRepository::save);

    return generatedUsers;
  }

  @SneakyThrows
  private GeneratedUsers loafFromResources() {
    ClassPathResource classPathResource = new ClassPathResource(FAKE_USERS_FILE);

    return objectMapper.readValue(classPathResource.getInputStream(), GeneratedUsers.class);
  }
}
