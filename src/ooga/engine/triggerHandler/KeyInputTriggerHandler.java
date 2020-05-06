package ooga.engine.triggerHandler;
import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Takes in inputs and returns triggers
 */
public class KeyInputTriggerHandler extends TriggerHandler {

    @Override
    public List<Trigger> getTriggers(List<Entity> entities, List<String> keyInput) {
        List<Trigger> triggers = new ArrayList<Trigger>();
        for(Entity entity : entities){
            for(String key : keyInput){
                if(entity.getKeyBindings().containsKey(key)) {
                    String triggeredAction = entity.getKeyBindings().get(key);
                    Trigger keyTrigger = new Trigger(entity, triggeredAction);
                    triggers.add(keyTrigger);
                }
            }
        }
        return triggers;
    }

}
