package ua.com.kn921g.games.gamescoredesk.service.security;

/**
 * Service for checking password correctness
 * @author yegorchevardin
 * @version 0.0.1
 */
public interface PasswordService {
    boolean checkPassword(String rawPassword, String hashedPassword);
    String encodePassword(String rawPassword);
}
