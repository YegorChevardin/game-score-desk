package ua.com.kn921g.games.gamescoredesk.service.security.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.com.kn921g.games.gamescoredesk.service.security.JwtService;

@Component
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
  private final JwtService jwtService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jwtService.parseAccessToken(username);
  }
}
