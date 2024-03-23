package ua.com.kn921g.games.gamescoredesk.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.com.kn921g.games.gamescoredesk.generated.dto.ScoresInformationDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;

public interface ScoreService {
    @PreAuthorize("hasRole('ROLE_SEE_SCORE')")
    ScoresInformationDto findScoresInformation(Integer page, Integer size, String scoreType);

    @PreAuthorize("hasRole('ROLE_UPDATE_OWN_SCORE')")
    UserFullResponseDto resetScore(String password);

    @PreAuthorize("hasRole('ROLE_UPDATE_OWN_SCORE')")
    UserFullResponseDto setScore(Integer newScore);
}
