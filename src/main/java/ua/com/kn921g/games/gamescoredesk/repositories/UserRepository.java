package ua.com.kn921g.games.gamescoredesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.kn921g.games.gamescoredesk.models.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT COUNT(*) + 1 " +
            "FROM User u " +
            "WHERE u.totalScore > :scoreToSearch")
    Integer findUserPlaceByTotalScore(@Param("scoreToSearch") Integer scoreToSearch);

    @Query("SELECT COUNT(*) + 1 " +
            "FROM User u " +
            "WHERE u.lastScore > :scoreToSearch")
    Integer findUserPlaceByLastScore(@Param("scoreToSearch") Integer scoreToSearch);
}
