package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Pane;
import ooga.view.ViewActions;

public class LayoutComponent extends Component {
    private static final String LAYOUT_PATH = "javafx.scene.layout.";
    private Pane object;

    public LayoutComponent(String componentName, SimpleStringProperty language, ViewActions viewActions) {
        super(componentName, language, viewActions);
        object = createLayoutObject(componentName);
        object.setId(getCssTag(componentName));
        attachCSS(object, "vbox-comp");
    }

    @Override
    public Pane getNode() {
        return object;
    }

    private Pane createLayoutObject(String name) {
        try {
            Class classDefinition = Class.forName(LAYOUT_PATH+name);
            Object layout = classDefinition.newInstance();
            return (Pane) layout;
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            //TODO remove stack trace
            showPopUpError(getName("MethodName"));
        }
        return null;
    }

}
