package net.chuiev.selcommittee.exception;

/**
 * Created by Alex on 3/7/2016.
 */
public class UnmodifiableEntityException extends RuntimeException {
    public UnmodifiableEntityException(Throwable cause) {
        super(cause);
    }

    public UnmodifiableEntityException() {
    }
}
