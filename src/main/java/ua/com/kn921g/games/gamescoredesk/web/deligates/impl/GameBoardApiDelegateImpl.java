package ua.com.kn921g.games.gamescoredesk.web.deligates.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.com.kn921g.games.gamescoredesk.generated.api.GameBoardApiDelegate;
import ua.com.kn921g.games.gamescoredesk.generated.dto.ScoresInformationDto;
import ua.com.kn921g.games.gamescoredesk.generated.dto.UserFullResponseDto;
import ua.com.kn921g.games.gamescoredesk.service.ScoreService;

@Component
@RequiredArgsConstructor
public class GameBoardApiDelegateImpl implements GameBoardApiDelegate {
  private final ScoreService scoreService;

  @Override
  public ResponseEntity<ScoresInformationDto> findScore(
      Integer page, Integer size, String scoreType) {
    return ResponseEntity.ok(scoreService.findScoresInformation(page, size, scoreType));
  }

  @Override
  public ResponseEntity<UserFullResponseDto> resetScore(String oldPassword) {
    return ResponseEntity.ok(scoreService.resetScore(oldPassword));
  }

  @Override
  public ResponseEntity<UserFullResponseDto> setScore(Integer newScore) {
    return ResponseEntity.ok(scoreService.setScore(newScore));
  }
}
