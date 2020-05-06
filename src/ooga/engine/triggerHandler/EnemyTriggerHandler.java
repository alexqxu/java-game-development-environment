package ooga.engine.triggerHandler;

import ooga.engine.helperObjects.Action;
import ooga.engine.helperObjects.Move;
import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;
import ooga.utilities.exception.ReflectionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * This class triggers enemy and call a one unit vector forces incrementally and simulate automatic motion
 * @author  Abebe Amare
 */
public class EnemyTriggerHandler extends TriggerHandler{
    private double[] left ={-1,0};
    private double[] right ={1,0};
    private double[] up ={0,-1};
    private double[] down ={0,1};
    private static final String METHOD_ACCESSOR="get";
    private static final String ACTION_NAME="PreDefined";

    EnemyTriggerHandler(){

    }

    @Override
    public List<Trigger> getTriggers(List<Entity> BackEndEntities) {
        List<Trigger> triggersList = new ArrayList<>();
        for (Entity entity : BackEndEntities){
            if(entity.getParameterController().getPreDefinedMotionHandler().getSizeOfActions()!=0){
               Move current= entity.getParameterController().getPreDefinedMotionHandler().getCurrentAction();

                    Action action=new Action((ACTION_NAME));
                    try {
                        Method method=this.getClass().getDeclaredMethod(METHOD_ACCESSOR+current.getAction());
                        double[] ar= (double[]) method.invoke(this);
                        action.setForce(ar[0]*current.getSize(),ar[1]*current.getSize());

                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new ReflectionException(e.getMessage());
                    }

                    Trigger keyTrigger = new Trigger(entity, action);
                    triggersList.add(keyTrigger);
                }

        }

    return triggersList;

    }


    private double[] getLeft() {
        return left;
    }

    private double[] getRight() {
        return right;
    }

    private double[] getUp() {
        return up;
    }

    private double[] getDown() {
        return down;
    }


}
