package ooga.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ToggleSwitch extends StackPane {

  private static final int WIDTH = 50;
  private static final int HEIGHT = 25;
  private static final double RADIUS = 5.0;
  private static final int CIRCLE_SIZE = 23;
  private final Rectangle back = new Rectangle(WIDTH, HEIGHT);
  private final Button button = new Button();
  private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
  private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #000000;";
  private SimpleBooleanProperty state;

  public ToggleSwitch() {
    init();
    EventHandler<Event> click = event -> {
      if (state.getValue()) {
        button.setStyle(buttonStyleOff);
        back.setFill(Color.valueOf("#ced5da"));
        setAlignment(button, Pos.CENTER_LEFT);
        state.setValue(false);
      } else {
        button.setStyle(buttonStyleOn);
        back.setFill(Color.valueOf("#5A76A1"));
        setAlignment(button, Pos.CENTER_RIGHT);
        state.setValue(true);
      }
    };
    back.setOnMouseClicked(click);
    button.setOnMouseClicked(click);
  }


  private void init() {
    state = new SimpleBooleanProperty(true);
    getChildren().addAll(back, button);
    back.setArcHeight(HEIGHT);
    back.setArcWidth(HEIGHT);
    back.setFill(Color.valueOf("#ced5da"));
    button.setShape(new Circle(RADIUS));
    setAlignment(button, Pos.CENTER_LEFT);
    button.setPrefSize(CIRCLE_SIZE, CIRCLE_SIZE);
  }

  protected SimpleBooleanProperty getSimpleBooleanProperty(){
    return state;
  }

}