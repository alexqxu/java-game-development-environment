package ooga.engine.triggerHandler;

import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines what a trigger handler should do.
 * @author Alex Xu, Vineet Alaparthi, Achilles Dabrowski
 */
public abstract class TriggerHandler {

    /**
     * Call this method to get a list of triggers for the inputs.
     * @return
     */
    public List<Trigger> getTriggers(List<Entity> BackEndEntities, List<String> keyInputs){
        return new ArrayList<Trigger>();
    }

    public List<Trigger> getTriggers(List<Entity> BackEndEntities){
        return new ArrayList<Trigger>();
    }
}
