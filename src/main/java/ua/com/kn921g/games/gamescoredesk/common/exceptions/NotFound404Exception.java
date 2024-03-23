package ua.com.kn921g.games.gamescoredesk.common.exceptions;

import org.springframework.http.HttpStatus;

public class NotFound404Exception extends GameScoreBoardBaseException {
    public NotFound404Exception(String exception) {
        super(exception, HttpStatus.NOT_FOUND);
    }
}
