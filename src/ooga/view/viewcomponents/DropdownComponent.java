/**
 * Class to use reflection of string from xml to create a drop-down component
 */

package ooga.view.viewcomponents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import ooga.utilities.FileManager;
import ooga.view.ViewActions;

public class DropdownComponent extends Component {

    private static final String DEFAULT_LANGUAGE = "English";
    private static final String LANGUAGE_PATH = "src/ooga/view/resources/languages/";
    private SimpleStringProperty language;
    private ComboBox<String> comboBox;
    private FileManager fileManager;

    public DropdownComponent(String componentName, SimpleStringProperty language, ViewActions viewActions) {
        super(componentName, language, viewActions);
        fileManager = new FileManager();
        this.language = language;
        comboBox = new ComboBox();
        ObservableList<String> languages = getLanguages();
        comboBox.getItems().addAll(languages);
        comboBox.setValue(DEFAULT_LANGUAGE);
        comboBox.setOnAction(e->handle(comboBox.getValue()));
        getChildren().add(comboBox);
    }

    private ObservableList<String> getLanguages() {
        Collection<String> list = fileManager.getFiles(LANGUAGE_PATH);
        List<String> languageDisplay = new ArrayList<>();
        for (String language: list){
            int index = language.indexOf(".");
            languageDisplay.add(language.substring(0, index));
        }
        return FXCollections.observableArrayList(languageDisplay);
    }

    private void handle(String newLanguage) {
        this.language.set(newLanguage);
    }

}
