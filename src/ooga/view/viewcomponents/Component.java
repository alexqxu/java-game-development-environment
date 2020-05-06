/**
 * Abstract class to serve as the frameworks for using reflection to create UI elements
 */
package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ooga.view.ViewActions;
import ooga.xmlreaders.ImmutableViewParameterController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Component<E> extends Pane {

  private String name;
  private static final String RESOURCES_UI_PATH = "ooga/view/resources/languages/";
  private static final String REFLECTION_PATH = "ooga/view/resources/reflections/";
  private static final String METHODS_PATH = REFLECTION_PATH + "ReflectionButtonActions";
  private static final String CSS_TAG_PATH = REFLECTION_PATH + "CSSTags";
  private static final String DATA_PATH = "data/";
  private ResourceBundle myResources;
  private ResourceBundle methodResources;
  private ResourceBundle myCssTagResources;
  private ViewActions viewActions;
  private String gameName;

  public Component(String componentName, SimpleStringProperty language, ViewActions viewActions) {
    this.name = componentName;
    this.viewActions = viewActions;
    myResources = ResourceBundle.getBundle(RESOURCES_UI_PATH + language.getValue());
    methodResources = ResourceBundle.getBundle(METHODS_PATH);
    myCssTagResources = ResourceBundle.getBundle(CSS_TAG_PATH);
    language.addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        myResources = ResourceBundle.getBundle(RESOURCES_UI_PATH + newValue);
      }
    });
  }

  public String getCssTag(String name) {
    return myCssTagResources.getString(name);
  }

  /**
   * method to get the name of UI element
   *
   * @param name is the name of UI element from xml
   * @return the string name representation in properties file
   */
  public String getName(String name) {
    return myResources.getString(name);
  }

  /**
   * method to get the method to be used for reflection
   *
   * @param name the name of UI element
   * @return the name of method represented inb properties file
   */
  public String getMethodName(String name) {
    return methodResources.getString(name);
  }

  public Pane getNode() throws FileNotFoundException {
    return this;
  }

  public HBox createSideLabel(String labelText) {
    HBox hbox = new HBox();
    //TODO: Consider removing and placing in css
    hbox.setAlignment(Pos.BASELINE_CENTER);
    hbox.setSpacing(20);
    hbox.getChildren().add(new Label(myResources.getString(labelText)));
    return hbox;
  }

  protected void attachCSS(Node node, String className) {
    node.getStyleClass().add(0, className);
  }

  //TODO refactor because similar method in GameSelectionView

    public Collection getLevels(){
        List<String> levels = new ArrayList<>();
        File directoryPath = new File(DATA_PATH+gameName+"/");
        for (String s : directoryPath.list()) {
            if (isLevelFile(s)){
                levels.add(getLevelName(s));
            }
        }
        Collections.sort(levels);
        return Collections.unmodifiableList(levels);
    }

    private String getLevelName(String s){
        int index = s.indexOf(".");
        try {
            return getName(s.substring(0, index));
        }catch(MissingResourceException e)
        {
            String fileName = s.substring(0, index);
            String firstLetter = s.substring(0,1).toUpperCase();
            return firstLetter+fileName.substring(1);
        }
    }

  public void setGameName(String name) {
    gameName = name;
  }

  public boolean isInfoFromParser(String fileName) {
    return fileName.contains("parser_");
  }

  public String getGameName() {
    return gameName;
  }

  private boolean isLevelFile(String name) {
    return name.contains("level_");
  }

  protected void showPopUpError(String errorMessage){
        Scene alertScene = new Scene(new AnchorPane(), 300, 300);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Stack Trace Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.initOwner(alertScene.getWindow());
        alert.showAndWait();
    }
}
