package ua.com.kn921g.games.gamescoredesk.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base runtime exception for this project
 * @author yegorchevardin
 * @version 0.0.1
 */
@Getter
public abstract class GameScoreBoardBaseException extends RuntimeException {
    private final HttpStatus httpStatus;

    protected GameScoreBoardBaseException(String exception, HttpStatus httpStatus) {
        super(exception);
        this.httpStatus = httpStatus;
    }
}
