package net.chuiev.selcommittee.exception;

/**
 * Exception, which throws, then entity doesn't exist in DB.
 *
 * @author Oleksii Chuiev  *
 */
public class EntityNotExistsException extends RuntimeException {
    public EntityNotExistsException(Throwable cause) {
        super(cause);
    }

    public EntityNotExistsException() {
    }
}
