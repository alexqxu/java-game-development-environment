package ooga.parser.components;



import java.util.ResourceBundle;

/**
 * Job of this class is to parse and extract display/launcher info. Uses the DOM Parser API.
 * @author Alex Xu
 */
public class DisplayInfoParser extends AbstractParser {
    public static final String RESOURCES_PATH = "ooga.parser.properties.displayInfoProperties";
    public static final String XSD = "src/ooga/parser/validators/xsdSchema/displayinfo.xsd";
    public static final String DISPLAY_CONFIG_SUFFIX = "info.xml";

    public static final String COVER_IMAGE = "coverimage";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String CONTROLS = "controls";
    public static final String DESCRIPTION_IMAGE = "descriptionimage";

    private String myCoverImage;
    private String myTitle;
    private String myDescription;
    private String myControls;
    private String myDescriptionImage;
    private ResourceBundle myResources;

    public DisplayInfoParser(String filepath){
        super(filepath + DISPLAY_CONFIG_SUFFIX, XSD);
        myResources = ResourceBundle.getBundle(RESOURCES_PATH);
        extractCoverImage();
        extractDescriptionImage();
        extractTitle();
        extractDescription();
        extractControls();
    }

    public String getCoverImage(){
        return myCoverImage;
    }

    public String getDescriptionImage(){
        return myDescriptionImage;
    }

    public String getTitle(){
        return myTitle;
    }

    public String getDescription(){
        return myDescription;
    }

    public String getControls(){
        return myControls;
    }

    private void extractCoverImage(){
        myCoverImage = extractElementValue(myResources.getString(COVER_IMAGE));
    }
    private void extractTitle(){
        myTitle = extractElementValue(myResources.getString(TITLE));
    }

    private void extractControls() {
        myControls = extractElementValue(myResources.getString(CONTROLS));
    }

    private void extractDescription() {
        myDescription = extractElementValue(myResources.getString(DESCRIPTION));
    }

    private void extractDescriptionImage(){
        myDescriptionImage = extractElementValue(myResources.getString(DESCRIPTION_IMAGE));
    }
}