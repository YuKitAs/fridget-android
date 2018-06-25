package edu.kit.pse.fridget.server.exceptions;

public class UnauthorizedException extends RuntimeException {
    private static final String CONNECTION_PROBLEM_MSG = "Authorization is currently not possible. Please try again later.";
    private static final String BROKEN_TOKEN_MSG = "User identifier is not valid.";
    private final boolean connectionProblem;

    private UnauthorizedException(String message, boolean connectionProblem) {
        super(message);

        this.connectionProblem = connectionProblem;
    }

    public boolean isConnectionProblem() {
        return connectionProblem;
    }

    public static UnauthorizedException buildForConnectionProblem() {
        return new UnauthorizedException(CONNECTION_PROBLEM_MSG, true);
    }

    public static UnauthorizedException buildForBrokenToken() {
        return new UnauthorizedException(BROKEN_TOKEN_MSG, false);
    }
}
