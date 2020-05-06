package bugreport;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import ooga.model.Entity;
import ooga.view.GameView;
import ooga.view.ViewActions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestBackgroundImage  {

  @Test
  void testBackgroundImage(){
    String gameName = "SampleGame1";
    Stage primaryStage = null;
    SimpleStringProperty language = new SimpleStringProperty("English");
    Collection<Entity> myEntities = Collections.emptyList();
    ViewActions myViewActions = new ViewActions();
    try {
      GameView myGameView = new GameView(gameName, language, myEntities, myViewActions, primaryStage);
      assertNotEquals(myGameView, null);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
