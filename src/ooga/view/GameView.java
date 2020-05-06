/**
 * Class used to create the actual game screen which user interacts with
 */

package ooga.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ooga.model.Entity;
import ooga.parser.components.LevelInfoParser;
import ooga.utilities.Coordinate;
import ooga.utilities.EntityGameStatus;
import ooga.view.viewcomponents.ComponentFactory;
import ooga.xmlreaders.XMLKeyValuePairReader;

public class GameView extends ViewScene {

  private String gameName;
  private static final String VIEW_RESOURCES = "src/ooga/view/resources/xml";
  private static final String VIEW_DEFAULT_RESOURCE_PACKAGE = VIEW_RESOURCES + "/";
  private static final String UI_OBJECTS_RESOURCES = "ooga/view/resources/reflections";
  private static final String UI_OBJECTS_RESOURCE_PACKAGE = UI_OBJECTS_RESOURCES + ".ReflectionButtonActions";
  private static final String GAME_DISPLAY_EXTENSION = "display";
  private static final String XML_EXTENSION = ".xml";
  private static final String DEFAULT_LANGUAGE = "English";
  private static final String DATA_FOLDER = "data/Games/";
  private String displayGameFilePath;
  private ResourceBundle uiObjectsResources;
  private ComponentFactory componentFactory;
  private XMLKeyValuePairReader myGameXMLFileReader;
  private Collection<Entity> myEntities;
  private SimpleStringProperty keyInput;
  private SimpleStringProperty keyReleasedInput;
  private SimpleStringProperty buttonInput;
  private CameraView cameraView;
  private ImageView background;
  private LevelInfoParser levelInfoParser;
  private Stage myStage;
  private SimpleBooleanProperty moveToGameOver;
  private SimpleStringProperty language;
  private GameOverView myGameOverView;
  private Stage primaryStage;

  public GameView(String gameName, SimpleStringProperty language, Collection<Entity> myEntities, ViewActions viewActions, Stage gameViewStage) throws FileNotFoundException {
    super(language, viewActions);
    this.gameName = gameName;
    this.language = language;
    myStage = gameViewStage;
    this.myEntities = myEntities;
    keyInput = new SimpleStringProperty();
    keyReleasedInput = new SimpleStringProperty();
    componentFactory = new ComponentFactory(language, viewActions);
    uiObjectsResources = ResourceBundle.getBundle(UI_OBJECTS_RESOURCE_PACKAGE);
    buttonInput = new SimpleStringProperty("pause");
    moveToGameOver = new SimpleBooleanProperty(true);

  }

@Override
  public String createXMLFilePath(String gameName) {
    displayGameFilePath =
        VIEW_DEFAULT_RESOURCE_PACKAGE + gameName + GAME_DISPLAY_EXTENSION + XML_EXTENSION;
    return displayGameFilePath;
  }

  public void setUpLevel(String levelPath){
    levelInfoParser = new LevelInfoParser(DATA_FOLDER+gameName+levelPath);
  }
  /**
   * Method to start game scene on a different stage
   * @param primaryStage2 specifies then stage for the game view
   */
  public void start(Stage primaryStage2) throws FileNotFoundException, NoSuchObjectException {
    primaryStage = primaryStage2;
    myGameOverView = new GameOverView(language, primaryStage);
    primaryStage2.setScene(buildScene());
  }

  /**
   * method to build the game view scene
   * @return the scene containing UI elements for the actual game
   * @throws FileNotFoundException
   * @throws NoSuchObjectException
   */
  protected Scene buildScene() throws FileNotFoundException, NoSuchObjectException {
    SplitPane split = new SplitPane();
    Scene myGameScene = new Scene(split, 800, 600);
    split.setDividerPositions(0.35f, 0.6f);
    attachStyle(myGameScene, "GameView");

    Pane leftPane = getPane(myGameResourceBundle.getString("LeftPaneCss"));
    buildLeftSplitPane(leftPane);
    SplitPane.setResizableWithParent(leftPane, Boolean.FALSE);
    Pane rightPane = getPane(myGameResourceBundle.getString("RightPaneCss"));
    Entity player = getPlayerEntity();
    background = new ImageView(new Image(new FileInputStream(levelInfoParser.getBackgroundImage())));
    cameraView = new CameraView(player, myGameScene.heightProperty(), myGameScene.widthProperty());
    buildRightSplitPane(rightPane);
    cameraView.bindBackgroundImage(background);
    ImageView playerDisplay = cameraView.createPlayerDisplay(player);
    setUpGameOver(player);
    rightPane.getChildren().add(playerDisplay);
    split.getItems().addAll(leftPane, rightPane);
    SplitPane.setResizableWithParent(leftPane, Boolean.FALSE);
    myGameScene.setOnKeyPressed(e -> handleKeyPressed(e));
    cameraView.handleCamera(rightPane, playerDisplay,background);
    myGameScene.setOnKeyReleased(e->handleKeyReleased(e));
    rightPane.requestFocus();
    rightPane.setFocusTraversable(true);
    return myGameScene;
  }

  private void setUpGameOver(Entity player) {
    player.getEntityGameStatus().addListener(new ChangeListener<EntityGameStatus>() {
      @Override
      public void changed(ObservableValue<? extends EntityGameStatus> observable, EntityGameStatus oldValue, EntityGameStatus newValue) {
        if (newValue.getStatus().equals("dead")){
            primaryStage.setScene(myGameOverView.getScene());
        }

      }
    });
  }

  /**
   * method to handle the key released event
   * @param event represents the event for which a key was released
   */
  public void handleKeyReleased(KeyEvent event){
      keyReleasedInput.set(String.valueOf(event.getCode()));
  }

  /**
   * method to handle the key pressed event
   * @param event represents the event for which a key was pressed
   */
  public void handleKeyPressed(KeyEvent event) {
    keyInput.set(String.valueOf(event.getCode()));
  }

  private void buildLeftSplitPane(Pane leftPane) {
    VBox vbox = new VBox();
    HBox hbox = buildButtonsHBox();
    vbox.setId("vbox-prime");
    vbox.getChildren().addAll(buildScoreInfoLabels(), hbox);
    leftPane.getChildren().add(vbox);
  }

  private VBox buildScoreInfoLabels() {
    VBox gameInfoVBox = new VBox();

    Label scoreLabel = new Label("Score : " + getPlayerEntity().getEntityGameStatus().getValue().getScore());
    Label livesLabel = new Label("Lives : " + getPlayerEntity().getEntityGameStatus().getValue().getLives());
    getPlayerEntity().getEntityGameStatus().addListener(new ChangeListener<EntityGameStatus>() {
      @Override
      public void changed(ObservableValue<? extends EntityGameStatus> observable,
          EntityGameStatus oldValue, EntityGameStatus newValue) {
        scoreLabel.setText("Score : " + newValue.getScore());
        livesLabel.setText("Lives : "+newValue.getLives());
      }
    });

    gameInfoVBox.setId("labels-vbox");
    gameInfoVBox.getChildren().addAll(scoreLabel, livesLabel);
    return gameInfoVBox;
  }

  private HBox buildButtonsHBox() {
    HBox hbox = new HBox();
    Button button = new Button(myLanguagesTextResourceBundle.getString("Pause"));
    Button saveButton = new Button(myLanguagesTextResourceBundle.getString("Save"));
    button.setFocusTraversable(false);
    saveButton.setFocusTraversable(false);
    saveButton.setOnMousePressed(e-> saveFunction());
    button.setOnMouseClicked(e -> handlePressed(button));
    hbox.setId("button-hbox");
    hbox.getChildren().addAll(button, saveButton);
    return hbox;
  }

  private void saveFunction() {
    //TODO add save to ghost
  }

  private void handlePressed(Button button) {
    buttonInput.setValue(button.getText());
    String str = button.getText().equals("Resume") ? "Pause" : "Resume";
    button.setText(str);
  }

  private void buildRightSplitPane(Pane rightPane) throws FileNotFoundException {
    rightPane.getChildren().add(background);
    Consumer<ImageView> resize = cameraView.getBindingCosumer();
    Entity player = getPlayerEntity();
    for (Entity entity : myEntities) {
      if (entity != player) {
        Image entityImage = new Image(
            new FileInputStream(entity.getParameterController().getImage()));
        ImageView entityImageView = new ImageView(entityImage);
        entityImageView.setFitHeight(entity.getBounds().getHeight());
        entityImageView.setFitWidth(entity.getBounds().getWidth());
        updatePosition(entity, entityImageView);
        entityImageView.setX(entity.getCoordinate().getX());
        entityImageView.setY(entity.getCoordinate().getY());

        entity.getEntityGameStatus().addListener(new ChangeListener<EntityGameStatus>() {
          @Override
          public void changed(ObservableValue<? extends EntityGameStatus> observable, EntityGameStatus oldValue, EntityGameStatus newValue) {
            if(newValue.getStatus().equals("dead")){
              entityImageView.setVisible(false);
            }
          }
        });
        rightPane.getChildren().addAll(entityImageView);
        createEntity(rightPane, resize, entity);
      }
    }
  }

  private void createEntity(Pane rightPane, Consumer<ImageView> resize, Entity entity) throws FileNotFoundException {
    Image entityImage = new Image(
        new FileInputStream(entity.getParameterController().getImage()));
    ImageView entityImageView = new ImageView(entityImage);
    entityImageView.setFitHeight(entity.getBounds().getHeight());
    entityImageView.setFitWidth(entity.getBounds().getWidth());
    updatePosition(entity, entityImageView);
    updateLife(entity, entityImageView);
    entityImageView.setX(entity.getCoordinate().getX());
    entityImageView.setY(entity.getCoordinate().getY());
    resize.accept(entityImageView);
    rightPane.getChildren().addAll(entityImageView);
  }

  private void updateLife(Entity entity, ImageView entityImageView) {
    entity.getEntityGameStatus().addListener(new ChangeListener<EntityGameStatus>() {
      @Override
      public void changed(ObservableValue<? extends EntityGameStatus> observable,
                          EntityGameStatus oldValue, EntityGameStatus newValue) {
        if (newValue.getStatus().equals("dead"))
          entityImageView.setVisible(false);
      }
    });
  }

  private void updatePosition(Entity entity, ImageView entityImageView) {
    entity.getDynamicEntityCoordinate().addListener(new ChangeListener<Coordinate>() {
      @Override
      public void changed(ObservableValue<? extends Coordinate> observable, Coordinate oldValue, Coordinate newValue) {
        entityImageView.setX(newValue.getX());
        entityImageView.setY(newValue.getY());
      }
    });
  }

  private Pane getPane(String style) {
    Pane paneSection = new Pane();
    paneSection.setStyle(style);
    return paneSection;
  }

  /**
   * method to get the key input pressed
   * @return the key input pressed
   */
  public SimpleStringProperty getKeyInput() {
    return keyInput;
  }

  /**
   * method to get the key input released
   * @return the key input released
   */
  public SimpleStringProperty getKeyReleasedInput(){
    return keyReleasedInput;
  }

  /**
   * method to tell back end if play, pause, save or restart button is hit
   * @return the button input required from user
   */
  public SimpleStringProperty getButtonInput() {
    return buttonInput;
  }

  private Entity getPlayerEntity() {
    for (Entity entity : myEntities) {
      if (entity.getName().contains("Player_1")) {
        return entity;
      }
    }
    return null;
  }
}
