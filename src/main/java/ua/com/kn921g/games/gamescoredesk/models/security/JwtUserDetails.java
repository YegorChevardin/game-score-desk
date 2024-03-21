package ua.com.kn921g.games.gamescoredesk.models.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetails extends User {
  public JwtUserDetails(String userId, Collection<? extends GrantedAuthority> authorities) {
    super(userId, "", authorities);
  }
}
