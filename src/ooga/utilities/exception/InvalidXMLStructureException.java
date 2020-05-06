package ooga.utilities.exception;

/**
 * Thrown when there is an invalid XML structure detected, defined by the schema
 * @author Alex Xu
 */
public class InvalidXMLStructureException extends RuntimeException {
    public static final String INVALID_XML_STRUCTURE = "(Invalid XML Config Structure)";
    public InvalidXMLStructureException(String message){
        super(INVALID_XML_STRUCTURE + message);
    }
}
