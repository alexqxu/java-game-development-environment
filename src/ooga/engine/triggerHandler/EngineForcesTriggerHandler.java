package ooga.engine.triggerHandler;
import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles triggers for the Universal Engine forces
 * @author Alex Xu, ABEBE AMARE
 */
public class EngineForcesTriggerHandler extends TriggerHandler {
    private static final String ACTION_TYPE="ExternalForce";
    private static final String ETHER="Ether";
    private static final String INTERACTION_TYPE="TOP";

    @Override
    public List<Trigger> getTriggers(List<Entity> BackEndEntities) {
        List<Trigger> triggersList = new ArrayList<>();
        for(Entity entity: BackEndEntities){
            if(isEngineForceActive(entity) && entity.checkFalling()){
                String triggeredAction = ACTION_TYPE;
                triggersList.add(new Trigger(entity, triggeredAction));
            }
        }
        return triggersList;
    }

    private boolean isEngineForceActive(Entity entity){
        return entity.interactionObjectDefined(ETHER) &&
                entity.overallInteractionDefined(ETHER,INTERACTION_TYPE,ACTION_TYPE);

    }
}
