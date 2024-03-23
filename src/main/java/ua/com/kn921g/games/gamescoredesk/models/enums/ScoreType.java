package ua.com.kn921g.games.gamescoredesk.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScoreType {
  TOTAL_SCORE("totalScore"),
  LAST_SCORE("lastScore");

  private final String name;
}
