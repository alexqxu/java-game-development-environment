/**
 * Game selection view is the class that shows the scene for selecting game options
 */
package ooga.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.parser.components.DisplayInfoParser;
import ooga.utilities.FileManager;
import ooga.view.viewcomponents.ButtonComponent;
import ooga.view.viewcomponents.ComponentFactory;
import ooga.view.viewcomponents.ImageComponent;
import ooga.xmlreaders.XMLKeyValuePairReader;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class GameSelectionView extends ViewScene {

    private static final String VIEW_RESOURCES = "src/ooga/view/resources/xml";
    private static final String VIEW_DEFAULT_RESOURCE_PACKAGE = VIEW_RESOURCES + "/";
    private static final String UI_OBJECTS_RESOURCES = "ooga/view/resources/reflections";
    private static final String UI_OBJECTS_RESOURCE_PACKAGE =
            UI_OBJECTS_RESOURCES + ".ReflectionButtonActions";
    private static final String EXTENSION = "display";
    private static final String XML_EXTENSION = ".xml";
    private static final String DATA_PATH = "data/Games/";
    private Scene myScene;
    private ButtonComponent back;
    private ViewActions viewActions;
    private SimpleStringProperty language;
    private FileManager fileManager;
    private SimpleBooleanProperty toggleState;

    public GameSelectionView(SimpleStringProperty language, ViewActions viewActions)
            throws IOException, SAXException, ParserConfigurationException {
        super(language, viewActions);
        this.viewActions = viewActions;
        this.language= language;
        this.toggleState = new SimpleBooleanProperty(true);
        fileManager = new FileManager();
        VBox selectionVBox = new VBox();
        selectionVBox.setId("selection-vbox");
        selectionVBox.getChildren().addAll(createTop(), buildImageScene());
        myScene = new Scene(selectionVBox);
        attachStyle(myScene, "SelectionView");
    }

    private Node createTop() throws FileNotFoundException {
        VBox myTopVBox = new VBox();
        back = new ButtonComponent("loginScene",language,viewActions);
        myTopVBox.getChildren().addAll(back, createHeaderComponents());
        toggleState = getToggleState();
        addListenerToToggleState(toggleState);
        myTopVBox.setId("top-vbox");
        return myTopVBox;
    }

    private void addListenerToToggleState(SimpleBooleanProperty toggleState) {
        toggleState.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                Boolean newValue) {
                if (newValue){
                    detachStyle(myScene, "DarkSelectionView");
                    attachStyle(myScene, "SelectionView");
                }
                else{
                    detachStyle(myScene, "SelectionView");
                    attachStyle(myScene, "DarkSelectionView");
                }
                toggleState.setValue(newValue);
            }
        });
    }

    private ScrollPane buildImageScene() throws FileNotFoundException {
        Collection<String> games = getGames();
        ScrollPane scrollPane = new ScrollPane();
        HBox hbox = new HBox();
        hbox.setId("hbox");
        for (String gameName: games){
                ImageComponent image = new ImageComponent("parser_CoverImage", language, viewActions, gameName);
                hbox.getChildren().add(image);
        }
        scrollPane.setContent(hbox);
        return scrollPane;
    }

    private Pane buildListScene(){
        ListView<String> listView = new ListView<String>();
        listView.getItems().addAll(getGames());
        listView.getSelectionModel().selectedItemProperty().addListener(
                e ->handleSceneTransition(listView));
        VBox vbox = new VBox(listView);
        return vbox;
    }

    private void handleSceneTransition(ListView<String> listView) {
        listView.refresh();
        back.setFocusTraversable(true);
        back.requestFocus();
        viewActions.handleGoDescriptionScene(listView.getSelectionModel().getSelectedItem());
    }

    public Scene getScene() {
        return myScene;
    }

    public Collection<String> getGames() {
        return fileManager.getDirectories(DATA_PATH);
    }

}

