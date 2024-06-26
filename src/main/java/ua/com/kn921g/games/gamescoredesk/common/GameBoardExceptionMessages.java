package ua.com.kn921g.games.gamescoredesk.common;

import lombok.Getter;

@Getter
public enum GameBoardExceptionMessages {
  ROLE_NOT_FOUND("Role %s not found"),
  USER_NOT_AUTHORIZED("User is not authorized"),
  TOKEN_IS_NOT_VALID("Token is not valid"),
  USER_NOT_FOUND("User not found"),
  USER_EXISTS("User with such username already exists"),
  CREDENTIALS_NOT_VALID("Credentials are not valid"),
  CREDENTIALS_NOT_VALID_DETAILED("Credentials are not valid (username: %s, password: %s)"),
  DTO_IS_NOT_VALID("Dto is not valid"),
  SCORE_TYPE_IS_NOT_VALID("Score type is not valid"),
  SCORE_IS_NOT_VALID("Score is not valid");

  private final String message;

  GameBoardExceptionMessages(String message) {
    this.message = message;
  }
}
