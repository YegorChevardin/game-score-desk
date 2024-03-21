package ua.com.kn921g.games.gamescoredesk.common.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequest400Exception extends GameScoreBoardBaseException {
    public BadRequest400Exception(String exception) {
        super(exception, HttpStatus.BAD_REQUEST);
    }
}
