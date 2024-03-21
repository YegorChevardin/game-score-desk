package ua.com.kn921g.games.gamescoredesk.common.exceptions.impl;

import ua.com.kn921g.games.gamescoredesk.common.exceptions.BadRequest400Exception;

public class DataNotValidException extends BadRequest400Exception {
  public DataNotValidException(String exception) {
    super(exception);
  }
}
