/**
 * Component factory class to create text field
 */
package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.scene.layout.Pane;
import ooga.utilities.exception.ReflectionException;
import ooga.view.ViewActions;
import ooga.xmlreaders.ImmutableViewParameterController;


public class TextFieldComponent extends Component {
    private TextField textField;

    public TextFieldComponent(String componentName, SimpleStringProperty language, ViewActions viewActions) {
        super(componentName, language, viewActions);
        textField = new TextField(getName(componentName));
        textField.setPromptText(getName(componentName));
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                handle(componentName, textField.getText());
        });
        textField.setOnMouseClicked(e -> textField.clear());
        Pane pane = createSideLabel(componentName);
        pane.getChildren().add(textField);
        getChildren().add(pane);
    }

    private void handle(String name, String text) {
        try {
            Method m = this.getClass().getDeclaredMethod(getMethodName(name), String.class);
            m.invoke(this, text);
        } catch (RuntimeException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            showPopUpError(getName("MethodError"));
        }
    }
}
