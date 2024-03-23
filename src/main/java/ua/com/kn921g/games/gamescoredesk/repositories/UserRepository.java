package ua.com.kn921g.games.gamescoredesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.kn921g.games.gamescoredesk.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query("SELECT COUNT(*) + 1 " +
            "FROM User u " +
            "WHERE u.totalScore > (SELECT u2.totalScore FROM User u2 WHERE u2.id = :userId)")
    Integer findUserPlaceByTotalScore(@Param("userId") UUID userId);

    @Query("SELECT COUNT(*) + 1 " +
            "FROM User u " +
            "WHERE u.lastScore > (SELECT u2.totalScore FROM User u2 WHERE u2.id = :userId)")
    Integer findUserPlaceByLastScore(@Param("userId") UUID userId);
}
