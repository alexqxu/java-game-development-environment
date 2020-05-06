/**
 * Abstract class to help easy attachment of css styles to front end features
 */
package ooga.view;

import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public abstract class StyledView {

  private static final String RESOURCES = "ooga/view/resources/languages";
  private static final String STYLED_RESOURCES = "ooga/view/resources/filepaths";
  private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
  private static final String STYLED_DEFAULT_RESOURCE_PACKAGE = STYLED_RESOURCES + ".";

  //Copied from GameDescriptionView - hoping to remove these variables from that class
  private static final String VIEW_RESOURCES = "src/ooga/view/resources/xml";
  private static final String VIEW_DEFAULT_RESOURCE_PACKAGE = VIEW_RESOURCES + "/";
  private static final String UI_OBJECTS_RESOURCES = "ooga/view/resources/reflections";
  private static final String UI_OBJECTS_RESOURCE_PACKAGE =
          UI_OBJECTS_RESOURCES + ".ReflectionButtonActions";
  private static final String DESCRIPTION_EXTENSION = "description";
  private static final String XML_EXTENSION = ".xml";
  protected ResourceBundle myGameResourceBundle;
  private ResourceBundle myStyledSheetsResourceBundle;
  private SimpleStringProperty language;

  public StyledView(SimpleStringProperty language) {
    language.addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
                          String newValue) {
        createResourceBundle(newValue);
      }
    });
    this.language = language;
    createResourceBundle(language.getValue());
    //createStyleSheetsResourceBundle();
  }
  //TODO Combine into one setter method?
  /**
   * method to set up css styling for node of UI element
   *
   * @param node      serves as node of UI element
   * @param className is the string in css for the style
   */
  protected void attachCSS(Node node, String className) {
    node.getStyleClass().add(0, className);
  }

  /**
   * Adds a stylesheet to a node of a group of UI elements
   *
   * @param styleSheet The path to the stylesheet
   */
  protected void attachStyle(Scene scene, String styleSheet) {
    scene.getStylesheets().add(myStyledSheetsResourceBundle.getString(styleSheet));
  }

  /**
   * Removes a stylesheet to the
   *
   * @param styleSheet The path to the stylesheet
   */
  protected void detachStyle(Pane node, String styleSheet) {
    node.getStylesheets().remove(styleSheet);
  }

  /**
   * @param propertiesItemName
   * @return the
   */
  protected String getString(String propertiesItemName) {
    return myGameResourceBundle.getString(propertiesItemName);
  }

  private void createStyleSheetsResourceBundle() {
    String fileName = STYLED_DEFAULT_RESOURCE_PACKAGE + "FilePaths";
    myStyledSheetsResourceBundle = ResourceBundle.getBundle(fileName);
  }

  private void createResourceBundle(String language) {
    String fileName = DEFAULT_RESOURCE_PACKAGE + language;
    myGameResourceBundle = ResourceBundle.getBundle(fileName);
  }

  //TODO if not used
  protected SimpleStringProperty getLanguage() {
    return language;
  }

  public String createXMLFilePath(String extension) {
    String descriptionFilePath =
            VIEW_DEFAULT_RESOURCE_PACKAGE + extension + XML_EXTENSION;
    return descriptionFilePath;
  }


}
