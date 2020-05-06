package ooga.utilities.exception;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class ReflectionException extends RuntimeException {
  public ReflectionException(String message){
    super(message);
  }

}
