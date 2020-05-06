package ooga.parser.validators;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

import ooga.utilities.exception.InvalidXMLStructureException;
import org.xml.sax.SAXException;


/**
 * Validates an XML configuration file against a defined XML Schema File
 * Example: The Config class calls the XML validator to validate the XMl structure before parsing.
 * @author Alex Xu, aqx
 */

public class XMLValidator {

    private XMLValidator(){
        throw new AssertionError();
    }

    /**
     * Validates a XML file against the XSD file that is given as part of the program.
     * @param xmlFile the file to be validated
     * @param xsdPath path of the schema to be validated against
     * @return true if the document structure is valid, false otherwise.
     */
    public static boolean validateXMLStructure(String xmlFile, String xsdPath){
        try {
            File myFile = new File(xmlFile);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(myFile));
        }catch(IOException | SAXException e){
            throw new InvalidXMLStructureException(e.getMessage());
        }
        return true;
    }
}