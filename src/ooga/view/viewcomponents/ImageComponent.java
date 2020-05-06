package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ooga.parser.components.DisplayInfoParser;
import ooga.utilities.exception.ReflectionException;
import ooga.view.ViewActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ImageComponent extends Component {
    private ImageView imageView;
    private static final String DATA_PATH = "data/Games/";
    private ViewActions viewActions;
    private static final String FILE_PATH = "ooga/view/resources/filepaths/FilePaths";
    private DisplayInfoParser displayInfoParser;
    private static final String GET = "get";
    private String componentName;
    private ResourceBundle imagePathResources;
    private static final String unable_to_show_image = "Unable to show image";

    public ImageComponent(String componentName, SimpleStringProperty language, ViewActions viewActions) throws FileNotFoundException {
        super(componentName, language, viewActions);
        this.componentName = componentName;
    }

    private void createImageComponent(String componentName, ViewActions viewActions) throws FileNotFoundException {
        this.viewActions = viewActions;
        imagePathResources = ResourceBundle.getBundle(FILE_PATH);
        String path = getImagePath(componentName);
        Image image = new Image(new FileInputStream(path));
        imageView = new ImageView(image);
        getChildren().add(imageView);
        imageView.setId(getCssTag(componentName));
        this.setOnMouseClicked(e->handleAction(componentName));
    }

    public ImageComponent(String componentName, SimpleStringProperty language, ViewActions viewActions, String gameName) throws FileNotFoundException {
        super(componentName, language, viewActions);
        setGameName(gameName);
        displayInfoParser = new DisplayInfoParser(DATA_PATH+getGameName()+"/");
        createImageComponent(componentName, viewActions);
    }

    private String getImagePath(String componentName) {
        try {
            String path = isInfoFromParser(componentName)? DATA_PATH+getGameName()+"/"+getParserImage(componentName)
                    : imagePathResources.getString(componentName);
            return path;
        }catch (RuntimeException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
            //e.printStackTrace();
            showPopUpError(getName("MethodError"));
            return null;
        }
    }

    private String getParserImage(String componentName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method =displayInfoParser.getClass().getDeclaredMethod(GET+componentName.split("_")[1]);
        String m = (String) method.invoke(displayInfoParser);
       return m;
    }

    private void handleAction(String name) {
        try {
            Method m = this.getClass().getDeclaredMethod(getMethodName(name));
            m.invoke(this);
        } catch (RuntimeException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            showPopUpError(getName("MethodError"));
        }
    }

    private void selectGame() {
        viewActions.handleGoDescriptionScene(getGameName());
    }

    public void emptyMethod(){

    }
    @Override
    public Pane getNode() {
        displayInfoParser = displayInfoParser = new DisplayInfoParser(DATA_PATH+getGameName()+"/");
        try {
            createImageComponent(componentName, viewActions);
        } catch (FileNotFoundException e) {
            showPopUpError(unable_to_show_image);
        }
        return this;
    }
}
