/**
 * Class to setup component factory
 */
package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import ooga.view.ViewActions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ComponentFactory {

    private SimpleStringProperty language;
    private static final String CLASS_PATH = ComponentFactory.class.getPackageName() + ".";
    private ViewActions viewActions;

    public ComponentFactory(SimpleStringProperty language, ViewActions viewActions){
        this.language = language;
        this.viewActions = viewActions;
    }
    public Component getComponent(String componentType, String componentValues){
        return createReflectedObject(componentType, componentValues);
    }

    public Component getComponent(String componentType, String componentValues, String gameName){
        Component component = createReflectedObject(componentType, componentValues);
        component.setGameName(gameName);
        return component;
    }

    private Component createReflectedObject(String componentType, String componentValues) {
        try {
            Class<?> clazz = Class.forName(CLASS_PATH+componentType);
            Constructor<?> constructor = clazz
                    .getDeclaredConstructor(String.class,
                            SimpleStringProperty.class, ViewActions.class);
            Component component = (Component) constructor.newInstance(componentValues, language, viewActions);
            return component;
        } catch (RuntimeException | ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e){
            throw new RuntimeException();
        }
    }

}
