package ooga.view;

import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Window;
import javafx.util.Pair;
import ooga.parser.components.DisplayInfoParser;
import ooga.view.viewcomponents.Component;
import ooga.view.viewcomponents.ComponentFactory;
import ooga.view.viewcomponents.DropdownComponent;
import ooga.xmlreaders.XMLKeyValuePairReader;
import org.w3c.dom.Element;

public abstract class ViewScene extends StyledView {

  protected static final String COMMA_DELIMITER = ",";
  protected static final String PLAYER_CSV_FILE = "src/ooga/utilities/csv/playerinfo.csv";
  private static final String STYLED_RESOURCES = "ooga/view/resources/filepaths";
  private static final String LANGUAGE_RESOURCES = "ooga/view/resources/languages";
  private static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = LANGUAGE_RESOURCES + ".";
  private static final String STYLED_DEFAULT_RESOURCE_PACKAGE = STYLED_RESOURCES + ".";
  private ResourceBundle myStyledSheetsResourceBundle;
  protected ResourceBundle myLanguagesTextResourceBundle;
  private Scene scene;
  private ComponentFactory componentFactory;
  private DisplayInfoParser displayInfoParser;
  private XMLKeyValuePairReader gameXMLFileReader;
  private SimpleBooleanProperty toggleState;
  private SimpleStringProperty language;
  private ViewActions viewActions;
  private String gameName;

  public ViewScene(SimpleStringProperty language, ViewActions myViewActions) {
    super(language);
    createStyleSheetsResourceBundle();
    String fileName = DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language.getValue();
    myLanguagesTextResourceBundle = ResourceBundle.getBundle(fileName);
    this.language = language;
    this.viewActions = myViewActions;
  }

  /**
   * Adds a stylesheet to the Scene
   *
   * @param styleSheet The path to the stylesheet
   */
  protected void attachStyle(Scene scene, String styleSheet) {
    if (scene != null) {
      scene.getStylesheets().add(myStyledSheetsResourceBundle.getString(styleSheet));
    }
  }

  /**
   * @param node      serves as node of UI element
   * @param className is the string in css for the style
   */
  protected void attachCSS(Node node, String className) {
    node.getStyleClass().add(0, className);
  }


  /**
   * Removes a stylesheet to the Scene
   *
   * @param styleSheet The path to the stylesheet
   */
  protected void detachStyle(Scene scene, String styleSheet) {
    if (scene != null) {
      scene.getStylesheets().remove(myStyledSheetsResourceBundle.getString(styleSheet));
    }
  }

  private void createStyleSheetsResourceBundle() {
    String fileName = STYLED_DEFAULT_RESOURCE_PACKAGE + "FilePaths";
    myStyledSheetsResourceBundle = ResourceBundle.getBundle(fileName);

  }

  /**
   * @param descriptionFilePath
   * @param descriptionSceneRoot
   */
  public void createLayout(String descriptionFilePath, HBox descriptionSceneRoot) throws FileNotFoundException {
    for (Element parentNode : gameXMLFileReader.getParentNodes(descriptionFilePath)) {
      Pair<String, String> nodePair = gameXMLFileReader.getNodePair(parentNode);
      Component node = componentFactory.getComponent(nodePair.getKey(), nodePair.getValue());
      addGameName(node);
      node.getNode().getChildren().add(getChildrenNodes(parentNode, node));
      descriptionSceneRoot.getChildren().add(node.getNode());
    }
  }

  /**
   * method to add the name of game to a node
   *
   * @param node serves as node of UI element
   */
  protected void addGameName(Component node) {
    if (gameName != null) {
      node.setGameName(gameName);
    }
  }

  /**
   * @param descriptionFilePath is a string
   * @return the built scene
   */
  protected Scene buildScene(String descriptionFilePath) throws FileNotFoundException {
    HBox descriptionSceneRoot = new HBox();
    descriptionSceneRoot.setSpacing(20);
    createLayout(descriptionFilePath, descriptionSceneRoot);
    Scene myGameDescriptionScene = new Scene(descriptionSceneRoot);
    attachStyle(myGameDescriptionScene, "DefaultLayout");
    return myGameDescriptionScene;
  }



  protected Scene buildScene(String descriptionFilePath, String gameName) throws FileNotFoundException {
    this.gameName= gameName;
    return buildScene(descriptionFilePath);
  }

  public Component getChildrenNodes(Element parentNode, Component parentComponent) throws FileNotFoundException, FileNotFoundException {
    if (parentNode.getFirstChild() == null) {
      return null;
    }
    for (Element element : gameXMLFileReader.getChildrenNodes(parentNode)) {
      if (element.getNodeName().equals("LayoutComponent")) {
        Pair<String, String> nodeValues = gameXMLFileReader.getNodePair(element);
        Component layout = componentFactory
            .getComponent(nodeValues.getKey(), nodeValues.getValue());
        layout.getNode().getChildren().add(getChildrenNodes(element, layout));
        parentComponent.getNode().getChildren().add(layout.getNode());
      } else {
        Pair<String, String> nodeValues = gameXMLFileReader.getNodePair(element);
        Component component = componentFactory
            .getComponent(nodeValues.getKey(), nodeValues.getValue());
        addGameName(component);
        parentComponent.getNode().getChildren().add(component.getNode());
      }
    }
    return parentComponent;
  }

  public void setComponentFactory(ComponentFactory componentFactory) {
    this.componentFactory = componentFactory;
  }

  public void setDisplayInfoParser(DisplayInfoParser displayParser) {
    displayInfoParser = displayParser;
  }

  public void setXMLReader(XMLKeyValuePairReader xmlReader) {
    gameXMLFileReader = xmlReader;
  }

  /**
   * method to show alert during login or sign up
   *
   * @param alertType represents the alert type for the pop up
   * @param owner     represents the
   * @param title
   * @param message
   */
  protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.showAndWait();
  }

  /**
   * method to create custom error pop up in front end
   *
   * @param errorMessage represents the error message obtained
   */
  protected void showPopUpError(String errorMessage) {
    Scene alertScene = new Scene(new AnchorPane(), 300, 300);
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Stack Trace Error");
    alert.setHeaderText(null);
    alert.setContentText(errorMessage);
    alert.initOwner(alertScene.getWindow());
    alert.showAndWait();
  }

  protected HBox createHeaderComponents() throws FileNotFoundException {
    HBox headerHBox = new HBox();
    DropdownComponent myDropDownComponent = new DropdownComponent("Language", this.language,
        this.viewActions);
    ToggleSwitch myToggle = new ToggleSwitch();
    toggleState = myToggle.getSimpleBooleanProperty();
    headerHBox.setSpacing(700);
    headerHBox.getChildren().addAll(myDropDownComponent.getNode(), myToggle);
    return headerHBox;
  }

  protected SimpleBooleanProperty getToggleState() {
    return toggleState;
  }

}
