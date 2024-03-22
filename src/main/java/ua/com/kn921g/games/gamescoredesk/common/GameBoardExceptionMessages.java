package ua.com.kn921g.games.gamescoredesk.common;

import lombok.Getter;

@Getter
public enum GameBoardExceptionMessages {
  ROLE_NOT_FOUND("Role %s not found"),
  USER_NOT_AUTHORIZED("User is not authorized"),
  TOKEN_IS_NOT_VALID("Token is not valid"),
  USER_NOT_FOUND("User not found"),
  CREDENTIALS_NOT_VALID("Credentials are not valid"),
  DTO_IS_NOT_VALID("Dto is not valid");

  private final String message;

  GameBoardExceptionMessages(String message) {
    this.message = message;
  }
}
