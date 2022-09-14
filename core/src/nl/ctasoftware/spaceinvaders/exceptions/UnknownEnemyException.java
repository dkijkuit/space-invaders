package nl.ctasoftware.spaceinvaders.exceptions;

public class UnknownEnemyException extends RuntimeException {
    public UnknownEnemyException(String message) {
        super(message);
    }
}
