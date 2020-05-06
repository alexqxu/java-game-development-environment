/**
 * Class to register new player for game
 */
package ooga.view;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ooga.utilities.exception.XMLException;

public class GameSignUpView extends ViewScene{

  private ViewActions myViewActions;
  private Scene myScene;
  private SimpleBooleanProperty toggleState;
  private Scene logInScene;

  public GameSignUpView(SimpleStringProperty language, ViewActions viewActions)
      throws IOException {
    super(language, viewActions);
    this.myViewActions = viewActions;
    this.myScene = this.buildScene();
    toggleState = new SimpleBooleanProperty(true);
  }

  /**
   * Method to obtain new scene when one transitions back to sign up screen
   * @return a new scene to handle user sign up
   */
  public Scene getScene() throws FileNotFoundException {
    return this.buildScene();
  }

  private Scene buildScene() throws FileNotFoundException {
    VBox vBox = new VBox();
    vBox.setId("main-vbox");
    GridPane gridPane = createRegistrationFormPane();
    vBox.getChildren().addAll(createHeaderComponents(), gridPane);
    toggleState = getToggleState();
    addListenerToToggleState(toggleState);
    logInScene = new Scene(vBox);
    addUIControls(gridPane);
    attachStyle(logInScene, "SignUpView");
    return logInScene;
  }

  private void addListenerToToggleState(SimpleBooleanProperty toggleState) {
    toggleState.addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
          Boolean newValue) {
        if (newValue){
          detachStyle(logInScene, "DarkSignUpView");
          attachStyle(logInScene, "SignUpView");
        }
        else{
          detachStyle(logInScene, "SignUpView");
          attachStyle(logInScene, "DarkSignUpView");
        }
        toggleState.setValue(newValue);
      }
    });
  }

  private GridPane createRegistrationFormPane() {
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setId("grid-pane");
    ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    columnOneConstraints.setHalignment(HPos.RIGHT);
    ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
    columnTwoConstrains.setHgrow(Priority.ALWAYS);
    gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);
    return gridPane;
  }

  private void addUIControls(GridPane gridPane) {
    setUpHeaderLabelOnGridPane(gridPane);
    setUpLabelOnGridPane(gridPane, myLanguagesTextResourceBundle.getString("NameLabel"), 1);
    TextField nameField = new TextField();
    setUpNameFieldOnGrid(gridPane, nameField, 1);
    setUpLabelOnGridPane(gridPane, myLanguagesTextResourceBundle.getString("EmailLabel"), 2);
    TextField emailField = new TextField();
    setUpEmailFieldOnGridPane(gridPane, emailField, 2);
    setUpLabelOnGridPane(gridPane, myLanguagesTextResourceBundle.getString("PasswordLabel"), 3);
    PasswordField passwordField = new PasswordField();
    setUpPasswordFieldOnGridPane(gridPane, passwordField, 3);
    Button submitButton = new Button(myLanguagesTextResourceBundle.getString("SignUpButton"));
    setUpSubmitButtonOnGridPane(gridPane, submitButton);
    Button LogInButton = new Button(myLanguagesTextResourceBundle.getString("LogInButton"));
    setUpLogInButtonOnGridPane(gridPane, LogInButton);
    LogInButton.setOnAction(e->myViewActions.handleSceneChange("goLoginScene"));
    submitButton.setOnAction(e-> {
      try {
        authenticateFieldText(gridPane, nameField, emailField, passwordField);
      } catch (IOException ex) {
        throw new XMLException();
      }
    });
  }

  private void setUpLabelOnGridPane(GridPane gridPane, String s, int i) {
    Label nameLabel = new Label(s);
    gridPane.add(nameLabel, 0, i);
  }

  private void setUpHeaderLabelOnGridPane(GridPane gridPane) {
    Label headerLabel = new Label(myLanguagesTextResourceBundle.getString("SignUpText"));
    headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    gridPane.add(headerLabel, 0,0,2,1);
    GridPane.setHalignment(headerLabel, HPos.CENTER);
    GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
  }

  private void setUpLogInButtonOnGridPane(GridPane gridPane, Button logInButton) {
    logInButton.setId("login-button");
    gridPane.add(logInButton, 0, 4, 2, 1);
  }

  private void setUpSubmitButtonOnGridPane(GridPane gridPane, Button submitButton) {
    submitButton.setId("signup-button");
    submitButton.setDefaultButton(true);
    gridPane.add(submitButton, 1, 4, 2, 1);
  }

  private void setUpPasswordFieldOnGridPane(GridPane gridPane, PasswordField passwordField, int i) {
    passwordField.setId("password-field");
    gridPane.add(passwordField, 1, i);
  }

  private void setUpEmailFieldOnGridPane(GridPane gridPane, TextField emailField, int i) {
    emailField.setId("email-field");
    gridPane.add(emailField, 1, i);
  }

  private void setUpNameFieldOnGrid(GridPane gridPane, TextField nameField, int i) {
    nameField.setId("name-field");
    gridPane.add(nameField, 1, i);
  }

  private void authenticateFieldText(GridPane gridPane, TextField nameField, TextField emailField, TextField passwordField)
      throws IOException {
    Scene alertScene = new Scene(new AnchorPane(), 300, 300);
    if(nameField.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("SignUpError"), myLanguagesTextResourceBundle.getString("NameFieldError"));
      return;
    }
    if(emailField.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("SignUpError"), myLanguagesTextResourceBundle.getString("EmailFieldError"));
      return;
    }
    if(passwordField.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("SignUpError"), myLanguagesTextResourceBundle.getString("PasswordFieldError"));
      return;
    }
    saveDataToCSV(nameField, emailField, passwordField, alertScene);
  }

  private String authenticateUserData(String[] csvData) {
    String name = csvData[1];
    String email = csvData[2];
    String validID = "";
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(PLAYER_CSV_FILE))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        if (values[1].equals(name) && values[2].equals(email)){
          validID = "0";
        }
        records.add(Arrays.asList(values));
      }
    } catch (IOException e) {
      //TODO Use pop up box
    }
    if (validID == "" && records.size() != 0) validID = "" + (Integer.parseInt(records.get(records.size()-1).get(0)) + 1);
    return validID;
  }

  private void saveDataToCSV(TextField nameField, TextField emailField, TextField passwordField, Scene alertScene)
      throws IOException {
    String comma = ",";
    String data = "0" + comma +  nameField.getText() + comma + emailField.getText() + comma + passwordField.getText();
    String[] csvData = data.split(",");
    String tempVal = authenticateUserData(csvData);
    if (tempVal == "0"){
      showAlert(AlertType.ERROR, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("RegisterUnsuccessful"),myLanguagesTextResourceBundle.getString("RegUnsuccessMessage") );
    }
    else{
      String newCSVRow =  tempVal + comma +  nameField.getText() + comma + emailField.getText() + comma + passwordField.getText() + "\n";
      helperToWriteToCSV(newCSVRow);
      showAlert(Alert.AlertType.CONFIRMATION, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("RegisterSuccessful"), myLanguagesTextResourceBundle.getString("RegSuccessMessage") + " "  +nameField.getText());
      myViewActions.handleSceneChange("goLoginScene");
    }
  }

  private void helperToWriteToCSV(String fileLine) throws IOException {
    BufferedReader br = null;
    BufferedWriter bw = null;
    String fileString = PLAYER_CSV_FILE;
    File file = new File(fileString);
    br = new BufferedReader(new FileReader(file));
    FileWriter fw = new FileWriter(fileString, true);
    bw = new BufferedWriter(fw);
    bw.write(fileLine);
    br.close();
    bw.close();
  }

}
