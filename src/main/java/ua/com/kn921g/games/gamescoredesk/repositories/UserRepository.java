package ua.com.kn921g.games.gamescoredesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kn921g.games.gamescoredesk.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
