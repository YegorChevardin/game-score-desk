package ua.com.kn921g.games.gamescoredesk.common.exceptions.impl;

import ua.com.kn921g.games.gamescoredesk.common.exceptions.BadRequest400Exception;

public class DataExistException extends BadRequest400Exception {
    public DataExistException(String exception) {
        super(exception);
    }
}
