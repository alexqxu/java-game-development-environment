package ooga.engine.physics;
import ooga.engine.helperObjects.Action;
import ooga.engine.helperObjects.Trigger;
import ooga.utilities.exception.EntityCreationError;
import ooga.utilities.exception.ReflectionException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class creates SubActionApplicator classes to apply a force on the entities using the type of action that is
 * exerted on the entities. It uses reflection to create the SubActionApplicators
 * @author Abebe Amare
 */
public class ActionApplicator {
    private static final String FILE_PATH="resources.engineProperties.applicatorInfo";
    private ResourceBundle textForApplicator = java.util.ResourceBundle.getBundle(FILE_PATH);
    private static final String ERROR_KEY="Error";
    private static final String METHOD_ACCESSOR="get";

    private String classPath="ooga.engine.physics.subApplicators.";
    private Class<?>[] pType;
    private static final String PACKAGE_SPLIT_REGEX="[.]";

    public ActionApplicator(List<Trigger> triggers){

        applyTriggers(triggers);


    }

    private void applyTriggers(List<Trigger> triggers) {
        for (Trigger trigger : triggers){
            for(Action act : trigger.getActions()) {
                String className = textForApplicator.getString(act.getAction());
                Constructor[] constructors;


                try {
                    constructors = Class.forName(classPath + className).getDeclaredConstructors();
                    this.pType = constructors[0].getParameterTypes();
                    Object[] ar = new Object[pType.length];
                    ar[0] = trigger.getEntity();
                    populateArgument(ar, act);
                    constructors[0].newInstance(ar);

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new EntityCreationError(textForApplicator.getString(ERROR_KEY) + className);
                }

            }
        }
    }

    private void populateArgument(Object[] ar, Action action) {

        for (int j = 1; j < pType.length; j++) {
            String [] str=pType[j].getName().split(PACKAGE_SPLIT_REGEX);
            String className = (str)[str.length - 1];

                try {
                    Method method=action.getClass().getMethod(METHOD_ACCESSOR+className);
                    ar[j]=method.invoke(action);

                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new ReflectionException(e.getMessage());
                }

        }


    }
}
