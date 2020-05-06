package ooga.view;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class DialogWindow extends Dialog {
    private static final String RESOURCES_PACKAGE = "ooga/view/resources/reflections/";
    private static final String LANGUAGES_PACKAGE = "ooga/view/resources/languages/";
    private static final String REFLECTION_PATH = RESOURCES_PACKAGE+"ReflectionButtonActions";

    private ResourceBundle reflectionsBundle;
    private ResourceBundle displayBundle;
    private Label display;
    private String selectedString;

    public DialogWindow(SimpleStringProperty language, Collection<String> list) {
        super();
        selectedString="";
        reflectionsBundle = ResourceBundle.getBundle(REFLECTION_PATH);
        displayBundle = ResourceBundle.getBundle(LANGUAGES_PACKAGE+language.getValue());
        setupWindow(list);
    }

    private void setupWindow(Collection list) {
        VBox vbox = createInputFields(list);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        //TODO Use Resource Bundle for display
        this.setTitle("Choose Level");
        this.setHeaderText("HeaderText");
        this.getDialogPane().setContent(vbox);
        setupInput(vbox);
    }

    private void setupInput(VBox vbox) {
        this.setResultConverter(new Callback<ButtonType, List<String>>() {
            @Override
            public List<String> call(ButtonType b) {
                if (b == ButtonType.OK) {
                    List<String> level = new ArrayList<>();
                    level.add(selectedString);
                    return level;
                }
                return null;
            }
        });
    }

    private VBox createInputFields(Collection<String> list) {
        VBox vbox = new VBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        for (String item: list){
            RadioButton button = new RadioButton(item);
            button.setToggleGroup(toggleGroup);
            button.setOnAction(e-> selectedString=button.getText());
            vbox.getChildren().add(button);
        }
        vbox.setSpacing(10);
        return vbox;
    }


}

