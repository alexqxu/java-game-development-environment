/**
 * Class to show Game over page when game/level is done
 */

package ooga.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOverView extends ViewScene {

  private static final String GAME_OVER_IMAGE_PATH = "src/ooga/view/resources/viewimages/game_over_image.jpg";

  private Stage primaryStage;

  public GameOverView(SimpleStringProperty language, Stage stage) {
    super(language, null);
    primaryStage = stage;
  }

  private Scene buildScene(){
    VBox vBoxMain = new VBox();
    Image image = null;
    try {
      image = new Image(new FileInputStream(GAME_OVER_IMAGE_PATH));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, false);
    BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
    Background background = new Background(backgroundImage);
    vBoxMain.setBackground(background);
    createVBoxElements(vBoxMain);
    Scene myScene = new Scene(vBoxMain);
    attachStyle(myScene, "GameOverView");
    return myScene;
  }
  public Scene getScene(){ return buildScene(); }

  private void createVBoxElements(VBox vBoxMain) {
    Label scoreLabel = new Label("Score = ");
    Button backToMainMenuButton = new Button("Main Menu");
    backToMainMenuButton.setOnMouseClicked(e->mainMenu());
    vBoxMain.getChildren().addAll(scoreLabel, backToMainMenuButton);
  }

  private void nextLevel() {
    //TODO transition to next level
  }

  private void mainMenu() {
    primaryStage.close();
  }

}
