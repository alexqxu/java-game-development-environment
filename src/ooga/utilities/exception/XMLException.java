package ooga.utilities.exception;

/**
 * This exception should be thrown when there is an error in XML Reading.
 * @author Alex Xu
 */
public class XMLException extends RuntimeException {
    public static final String MESSAGE = "INCORRECT XML FORMAT";
    public XMLException(){
        super(MESSAGE);
    }
}
