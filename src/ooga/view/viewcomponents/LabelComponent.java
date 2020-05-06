package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ooga.parser.components.DisplayInfoParser;
import ooga.view.ViewActions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LabelComponent extends Component {
    private Label label;
    private String componentName;
    private DisplayInfoParser displayInfoParser;
    private static final String DATA_PATH = "data/Games/";
    private static final String GET="get";
    public LabelComponent(String componentName, SimpleStringProperty language, ViewActions viewActions) {
        super(componentName, language, viewActions);
        this.componentName = componentName;
        label = new Label();
    }
    @Override
    public Pane getNode(){
        displayInfoParser = new DisplayInfoParser(DATA_PATH+getGameName()+"/");
        createLabel();
        return this;
    }

    private void createLabel() {
        String displayText= getDisplayText();
        label.setText(displayText);
        label.setWrapText(true);
        label.setId(getCssTag(componentName));
        this.getChildren().add(label);
    }

    private String getDisplayText() {
        try {
            String path = isInfoFromParser(componentName)? getParserText()
                    : getName(componentName);
            return path;
        }catch (RuntimeException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e){
            return  getName(componentName);
        }
    }

    private String getParserText() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method =displayInfoParser.getClass().getDeclaredMethod(GET+componentName.split("_")[1]);
        String m = (String) method.invoke(displayInfoParser);
        return m;
    }
}
