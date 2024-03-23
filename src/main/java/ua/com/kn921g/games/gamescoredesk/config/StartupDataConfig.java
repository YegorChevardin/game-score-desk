package ua.com.kn921g.games.gamescoredesk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ua.com.kn921g.games.gamescoredesk.models.GeneratedUsers;
import ua.com.kn921g.games.gamescoredesk.service.FakeProfilesGeneratorService;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupDataConfig implements ApplicationListener<ContextRefreshedEvent> {
  private final FakeProfilesGeneratorService fakeProfilesGeneratorService;
  private final ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public void onApplicationEvent(ContextRefreshedEvent event) {
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    GeneratedUsers generatedUsers = fakeProfilesGeneratorService.loadGeneratedUsers();
    log.info("Generated fake users was loaded, try to log in.");
    log.info(objectMapper.writeValueAsString(generatedUsers));
    objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
  }
}
