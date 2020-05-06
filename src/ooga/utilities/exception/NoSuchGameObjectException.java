package ooga.utilities.exception;

/**
 * Thrown when the Level requests an object type not defined in the objects file.
 * @author Alex Xu
 */
public class NoSuchGameObjectException extends RuntimeException{
    public static final String MESSAGE = "Requested an object type not defined in the objects file.";

    public NoSuchGameObjectException(){
        super(MESSAGE);
    }
}
