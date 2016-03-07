package net.chuiev.selcommittee.exception;

/**
 * Created by Алексей on 3/6/2016.
 */
public class EntityNotExistsException extends RuntimeException {
    public EntityNotExistsException(Throwable cause) {
        super(cause);
    }

    public EntityNotExistsException()
    {

    }
}
