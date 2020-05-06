/**
 * Class to create custom button component
 */

package ooga.view.viewcomponents;

import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import javafx.stage.Stage;
import ooga.controller.DataController;
import ooga.levelcreator.LevelEditor;
import ooga.utilities.FileManager;
import ooga.utilities.exception.ReflectionException;
import ooga.view.DialogWindow;
import ooga.view.GameView;
import ooga.view.ViewActions;

public class ButtonComponent extends Component {

  private Button button;
  private SimpleStringProperty language;
  private ViewActions viewActions;
  private SimpleStringProperty buttonListeners;
  private SimpleIntegerProperty keyListener;
  private static final String SRC = "src";

  public ButtonComponent(String componentName, SimpleStringProperty language,
      ViewActions viewActions) {
    super(componentName, language, viewActions);
    this.viewActions = viewActions;
    button = new Button(getName(componentName));
    button.setId(getCssTag(componentName));
    language.addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        button.setText(getName(componentName));
      }
    });
    button.setOnAction(e -> handleAction(componentName));
    button.setId(getCssTag(componentName));
    getChildren().add(button);
    this.language = language;
  }

  private void handleAction(String name) {
    try {
      Method m = this.getClass().getDeclaredMethod(getMethodName(name));
      m.invoke(this);
    } catch (RuntimeException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      showPopUpError(getName("MethodError"));
    }
  }


  private void playGame() throws FileNotFoundException, NoSuchObjectException {
    FileManager fileManager = new FileManager();
    String level = fileManager.getFileFromChooser();
    String dataPath = "data/Games/" + getGameName() + "/";//for backend
    String levelPath = level.substring(level.indexOf("\\level"));

    int levelNumber = getLevelNumber(levelPath); //for backend

    DataController dataController = new DataController(levelNumber, dataPath);
    Stage menuStage = new Stage();
    GameView myGameView = new GameView(getGameName(), language, dataController.getViewEntities(),
        viewActions, menuStage);
    myGameView.setUpLevel(levelPath);
    dataController.setControllers(myGameView.getButtonInput(), myGameView.getKeyInput(),
        myGameView.getKeyReleasedInput());
    dataController.playGame();
    myGameView.start(menuStage);
    menuStage.show();
  }

  private void playAgainstBot() throws FileNotFoundException, NoSuchObjectException {
    FileManager fileManager = new FileManager();
    String file = fileManager.getFileFromChooser("*.properties");
    String filePath = file.substring(file.indexOf(SRC));

    String dataPath = "data/Games/" + getGameName() + "/";
    int levelNumber = 1;

    try {
      DataController dataController = new DataController(levelNumber, dataPath);

      Stage menuStage = new Stage();
      GameView myGameView = new GameView(getGameName(), language, dataController.getViewEntities(),
          viewActions, menuStage);
      myGameView.setUpLevel("\\level_1.xml");
      dataController.setControllers(myGameView.getButtonInput(), myGameView.getKeyInput(),
          myGameView.getKeyReleasedInput());
      dataController.playGameWithBot(filePath);
      myGameView.start(menuStage);
      menuStage.show();
    } catch (RuntimeException e) {
      showPopUpError("Unable to load bots");
    }
  }

  private int getLevelNumber(String level) {
    return Integer.parseInt(level.substring(level.indexOf("_") + 1, level.indexOf(".")));
  }


  private void displayDialogWindow() {
    DialogWindow dialogWindow = new DialogWindow(language, getLevels());
    Optional<List<String>> result = dialogWindow.showAndWait();
  }

  /**
   * Method to move to selection screen when button is pressed
   */
  private void goSelectionScene() {
    viewActions.handleSceneChange("goSelectionScene");
  }

  /**
   * Method to move to login screen when button is pressed
   */
  private void goLoginScene() {
    viewActions.handleSceneChange("goLoginScene");
  }


  /**
   * Method to launch level editor when button is pressed
   *
   * @throws Exception
   */
  public void startLevelEditor() throws Exception {
    Stage levelEditor = new Stage();
    FileManager fileManager = new FileManager();
    String backgroundPath = fileManager.getFileFromChooser();
    LevelEditor grid = new LevelEditor(getGameName(), backgroundPath);
    grid.start(levelEditor);
    levelEditor.show();
  }

  private void goSignUpScene() {
    viewActions.handleSceneChange("goSignUpScene");
  }


}
