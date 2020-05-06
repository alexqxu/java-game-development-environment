/**
 * Class to create the login to game
 */
package ooga.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import ooga.view.viewcomponents.ButtonComponent;

public class GameLogInView extends ViewScene {

  private static final String LOGIN_IMAGE_PATH = "src/ooga/view/resources/viewimages/user_image.png";
  private Scene myScene;
  private ButtonComponent back;
  private ViewActions viewActions;
  private SimpleStringProperty language;
  private PasswordField passwordField;
  private TextField txtUserName;
  private String playerName;
  private SimpleBooleanProperty toggleState;
  private Scene scene;

  public GameLogInView(SimpleStringProperty language, ViewActions viewActions)
      throws FileNotFoundException {
    super(language, viewActions);
    this.viewActions = viewActions;
    this.language = language;
    this.myScene = this.buildScene();
    this.toggleState = new SimpleBooleanProperty(true);
  }

  public Scene getScene() throws FileNotFoundException {
    return this.buildScene();
  }

  private Scene buildScene() throws FileNotFoundException {
    VBox myVBox = getVBox();
    myVBox.setId("vbox-prime");
    scene = new Scene(myVBox);
    attachStyle(scene, "LogInView");
    return scene;
  }

  private Node createTop() {
    back = new ButtonComponent("SignUpScene", language, viewActions);
    return back;
  }

  private VBox getVBox() throws FileNotFoundException {
    VBox myVBox = new VBox();
    HBox buttonHBox = createButtonHBox();
    HBox textHBox = createTextHBox();
    GridPane gridPane = new GridPane();
    gridPane.setId("gridpane");
    createLogInImage(gridPane, "login_image", 0);
    createLabel(gridPane, myLanguagesTextResourceBundle.getString("EmailLabel"), 2);
    createUserNameTextField(gridPane);
    createLabel(gridPane, myLanguagesTextResourceBundle.getString("PasswordLabel"), 3);
    createPasswordTextField(gridPane);
    createLogInButton(gridPane);
    createMessageLabel(gridPane);
    myVBox.getChildren().addAll(buttonHBox, createHeaderComponents(), textHBox, gridPane);
    toggleState = getToggleState();
    addListenerToToggleState(toggleState);
    return myVBox;
  }

  private void addListenerToToggleState(SimpleBooleanProperty toggleState) {
    toggleState.addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
          Boolean newValue) {
        if (newValue){
          detachStyle(scene, "DarkLogInView");
          attachStyle(scene, "LogInView");
        }
        else{
          detachStyle(scene, "LogInView");
          attachStyle(scene, "DarkLogInView");
        }
        toggleState.setValue(newValue);
      }
    });
  }

  private void createLogInImage(GridPane gridPane, String login_image, int i)
      throws FileNotFoundException {
    Image loginImage = new Image(new FileInputStream(LOGIN_IMAGE_PATH));
    ImageView loginImageView = new ImageView(loginImage);
    gridPane.add(loginImageView, 1, i);
    GridPane.setHalignment(loginImageView, HPos.CENTER);
    GridPane.setMargin(loginImageView, new Insets(20, 0,20,0));
  }

  private HBox createTextHBox() {
    HBox tempHBox = new HBox();
    tempHBox.setId("Text-HBox");
    createTopLabel(tempHBox);
    return tempHBox;
  }

  private HBox createButtonHBox() {
    HBox tempHBox = new HBox();
    tempHBox.getChildren().add(createTop());
    return tempHBox;
  }

  private void createMessageLabel(GridPane gridPane) {
    final Label lblMessage = new Label();
    gridPane.add(lblMessage, 1, 2);
  }

  private void createTopLabel(HBox hBox) {
    DropShadow dropShadow = new DropShadow();
    dropShadow.setOffsetX(5);
    dropShadow.setOffsetY(5);
    Text text = new Text(myLanguagesTextResourceBundle.getString("LogInText"));
    text.setId("text");
    text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
    text.setEffect(dropShadow);
    text.setTextAlignment(TextAlignment.CENTER);
    hBox.getChildren().addAll(text);
  }

  private void createLogInButton(GridPane gridPane) {
    Button btnLogin = new Button(myLanguagesTextResourceBundle.getString("LogInButton"));
    btnLogin.setId("btnLogin");
    btnLogin.setOnAction(event -> buttonFunction());
    gridPane.add(btnLogin, 2, 3);
  }

  private void createPasswordTextField(GridPane gridPane) {
    passwordField = new PasswordField();
    passwordField.setId("password-field");
    gridPane.add(passwordField, 1, 3);
  }

  private void createUserNameTextField(GridPane gridPane) {
    txtUserName= new TextField();
    txtUserName.setId("user-field");
    gridPane.add(txtUserName, 1, 2);
  }

  private void createLabel(GridPane gridPane, String username, int i) {
    Label lblUserName = new Label(username);
    gridPane.add(lblUserName, 0, i);
  }

  private void buttonFunction() {
    Scene alertScene = new Scene(new AnchorPane(), 300, 300);
    if (txtUserName.getText().isEmpty() || passwordField.getText().isEmpty()){
      showAlert(Alert.AlertType.ERROR, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("LogInError"), myLanguagesTextResourceBundle.getString("LogInFieldError"));
      return;
    }
    checkUserAuthentication(alertScene);
  }

  private void checkUserAuthentication(Scene alertScene) {
    String [] userLoginData = (txtUserName.getText() + "," + passwordField.getText()).split(",");
    if (authenticateEmailIDAndPassword(userLoginData)){
      showAlert(Alert.AlertType.CONFIRMATION, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("LogInSuccess"), myLanguagesTextResourceBundle.getString("LogInSuccessMess"));
      viewActions.handleSceneChange("goSelectionScene");
    }
    else{
      showAlert(AlertType.ERROR, alertScene.getWindow(), myLanguagesTextResourceBundle.getString("LogInUnsuccess"),myLanguagesTextResourceBundle.getString("LogInUnsuccessMess" ));
    }
  }

  private boolean authenticateEmailIDAndPassword(String[] userLoginData) {
    String emailID = userLoginData[0];
    String password = userLoginData[1];
    boolean isPresent = false;
    try (BufferedReader br = new BufferedReader(new FileReader(PLAYER_CSV_FILE))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        if (values[2].equals(emailID) && values[3].equals(password)){
            playerName = values[1];
            isPresent = true;
        }
      }
    } catch (IOException e) {
      showPopUpError(e.getMessage());
    }
    return isPresent;
  }

  protected String getPlayerName(){
    return playerName;
  }

}
