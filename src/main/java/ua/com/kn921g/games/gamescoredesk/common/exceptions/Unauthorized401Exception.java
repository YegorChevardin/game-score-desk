package ua.com.kn921g.games.gamescoredesk.common.exceptions;

import org.springframework.http.HttpStatus;

public class Unauthorized401Exception extends GameScoreBoardBaseException {
    public Unauthorized401Exception(String exceptions) {
        super(exceptions, HttpStatus.UNAUTHORIZED);
    }
}
