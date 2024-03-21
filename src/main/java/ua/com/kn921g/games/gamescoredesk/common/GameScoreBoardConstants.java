package ua.com.kn921g.games.gamescoredesk.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameScoreBoardConstants {
  public static final String ACCESS_TOKEN_HEADER_NAME = "Authorization";
  public static final long ACCESS_TOKEN_EXPIRATION = 3600 * 1000;
  public static final String PERMISSIONS_FILE = "dictionary/permissions.json";
  public static final String ACCESS_TOKEN_PREFIX = "Bearer ";
}
