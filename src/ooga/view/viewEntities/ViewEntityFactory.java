package ooga.view.viewEntities;

import api.EntityFactoryAPI;
import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.utilities.exception.EntityCreationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * This class implements the EntityFactoryAPI and constructs game Entities using the name of the Entity given
 * getEntity method
 */
public class ViewEntityFactory implements EntityFactoryAPI {

    private static final String FILE_PATH="resources.dataProperties.FactoryProperties";
    private ResourceBundle textForFactory = java.util.ResourceBundle.getBundle(FILE_PATH);
    private static final String CLASSPATH = "ViewEntityFactoryPath";
    private static final String EXTENSION = "ViewEntity";
    private static final int FIRST_PARAM = 0;
    private static final String VIEW_ENTITY_ERROR = "ViewEntityFactoryError";


    @Override
    public Entity getEntity(String name, ParameterController par) {

        Constructor[] constructors;

        try {
            constructors = Class.forName(textForFactory.getString(CLASSPATH) + name + EXTENSION).getDeclaredConstructors();
        } catch (ClassNotFoundException e) {
            throw new EntityCreationError(textForFactory.getString(VIEW_ENTITY_ERROR)+name);
        }
        try {
            return (Entity) constructors[FIRST_PARAM].newInstance(par);
        } catch (InstantiationException|IllegalAccessException|InvocationTargetException e) {
            throw new EntityCreationError(textForFactory.getString(VIEW_ENTITY_ERROR)+name);
        }

    }

}
