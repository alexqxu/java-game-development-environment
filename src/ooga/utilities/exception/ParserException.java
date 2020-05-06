package ooga.utilities.exception;

/**
 * This exception should be thrown when there is an error in the Parsers.
 * @author Alex Xu
 */
public class ParserException extends RuntimeException{
    public ParserException(String message){
        super(message);
    }
}
