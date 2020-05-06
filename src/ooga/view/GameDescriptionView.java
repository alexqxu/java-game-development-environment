package ooga.view;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import ooga.parser.components.DisplayInfoParser;
import ooga.view.viewcomponents.ComponentFactory;
import ooga.xmlreaders.XMLKeyValuePairReader;

public class GameDescriptionView extends ViewScene {

  private static final String VIEW_RESOURCES = "src/ooga/view/resources/xml";
  private static final String VIEW_DEFAULT_RESOURCE_PACKAGE = VIEW_RESOURCES + "/";
  private static final String UI_OBJECTS_RESOURCES = "ooga/view/resources/reflections";
  private static final String UI_OBJECTS_RESOURCE_PACKAGE =
      UI_OBJECTS_RESOURCES + ".ReflectionButtonActions";
  private static final String DESCRIPTION_EXTENSION = "description";
  private static final String XML_EXTENSION = ".xml";
  private String descriptionFilePath;
  private ResourceBundle uiObjectsResources;
  private ComponentFactory componentFactory;
  private XMLKeyValuePairReader myGameXMLFileReader;
  private DisplayInfoParser displayInfoParser;
  private Scene myScene;

  public GameDescriptionView(String gameName, SimpleStringProperty language, ViewActions viewActions) throws FileNotFoundException {
    super(language, viewActions);
    descriptionFilePath = createXMLFilePath(DESCRIPTION_EXTENSION);
    myGameXMLFileReader = new XMLKeyValuePairReader();
    myGameXMLFileReader.read(descriptionFilePath);
    uiObjectsResources = ResourceBundle.getBundle(UI_OBJECTS_RESOURCE_PACKAGE);
    componentFactory = new ComponentFactory(language, viewActions);
    displayInfoParser = new DisplayInfoParser(String.format("data/Games/%s/", gameName));
    setComponentFactory(componentFactory);
    setDisplayInfoParser(displayInfoParser);
    setXMLReader(myGameXMLFileReader);
    myScene = buildScene(descriptionFilePath, gameName);
    attachStyle(myScene, "DescriptionView");
  }

  public Scene getScene(){
    return myScene;
  }

}
