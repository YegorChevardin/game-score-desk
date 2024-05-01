package ua.com.kn921g.games.gamescoredesk.service.security.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.kn921g.games.gamescoredesk.service.security.PasswordService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordServiceImpl implements PasswordService {
  private final PasswordEncoder passwordEncoder;

  @Override
  public boolean checkPassword(String rawPassword, String hashedPassword) {
    return passwordEncoder.matches(
        URLDecoder.decode(rawPassword, StandardCharsets.UTF_8), hashedPassword);
  }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
