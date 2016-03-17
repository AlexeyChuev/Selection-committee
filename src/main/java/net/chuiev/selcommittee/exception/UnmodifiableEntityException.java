package net.chuiev.selcommittee.exception;

/**
 * Exception, which throws, then user and admin can't modify entity in DB.
 *
 * @author Oleksii Chuiev  *
 */
public class UnmodifiableEntityException extends RuntimeException {
    public UnmodifiableEntityException(Throwable cause) {
        super(cause);
    }

    public UnmodifiableEntityException() {
    }
}
