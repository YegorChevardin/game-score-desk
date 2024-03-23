package ua.com.kn921g.games.gamescoredesk.common.exceptions.impl;

import ua.com.kn921g.games.gamescoredesk.common.exceptions.NotFound404Exception;

public class DataNotFoundException extends NotFound404Exception {
    public DataNotFoundException(String exception) {
        super(exception);
    }
}
