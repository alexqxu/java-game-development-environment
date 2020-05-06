package ooga.engine.helperObjects;

import ooga.model.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a data encapsulation for an entity-action pairing.
 * @author Alex Xu
 */
public class Trigger {
    private Entity myEntity;
    private List<Action> myActions;

    public Trigger(Entity entity, List<String> actions){
        myEntity = entity;
        myActions = new ArrayList<>();
        for(String act : actions){
            myActions.add(new Action(act));
        }
    }

    public Trigger(Entity entity, String action){
        myEntity = entity;
        myActions = new ArrayList<Action>(Arrays.asList(new Action(action)));
    }
    public Trigger(Entity entity, Action action){
        myEntity = entity;
        myActions = new ArrayList<Action>(Arrays.asList(action));
    }

    public List<String> getActionsAsStrings(){
        List<String> actionStrings = new ArrayList<>();
        for(Action act : myActions){
            actionStrings.add(act.getAction());
        }
        return actionStrings;
    }
    public List<Action> getActions(){
        return myActions;
    }

    public Entity getEntity(){
        return myEntity;
    }

}
