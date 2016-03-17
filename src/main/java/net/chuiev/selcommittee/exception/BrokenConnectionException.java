package net.chuiev.selcommittee.exception;

/**
 * Exception, which throws, then connection has problems and brokes.
 *
 * @author Oleksii Chuiev  *
 */
public class BrokenConnectionException extends RuntimeException {
    public BrokenConnectionException(Throwable cause) {
        super(cause);
    }
}
