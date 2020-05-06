package ooga.engine.triggerHandler;
import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class CollisionTriggerHandler extends TriggerHandler {
    @Override
    public List<Trigger> getTriggers(List<Entity> entities) {
        List<Trigger> triggers = new ArrayList<Trigger>();
        boolean breakLoop=false;
        for(Entity activeEntity : entities){
            for(Entity otherEntity : entities){
                if(!activeEntity.equals(otherEntity) && checkCollision(activeEntity, otherEntity)){
                    String collisionType = getCollisionType(activeEntity,otherEntity);
                    if(!collisionType.equals("NONE")){
                        List<String> triggeredAction = activeEntity.getCollisionAction(otherEntity.getParameterController().getType(), collisionType);
                        triggers.add(new Trigger(activeEntity, triggeredAction));
                    }
                    String collisionType2 = getCollisionType(otherEntity,activeEntity);
                    if(!collisionType2.equals("NONE")){
                        List<String> triggeredAction2 = otherEntity.getCollisionAction(activeEntity.getParameterController().getType(),
                                collisionType2);
                        triggers.add(new Trigger(otherEntity, triggeredAction2));

                    }

                    breakLoop=true;
                    break;
                }
            }
           if(breakLoop){
               break;
           }
        }

        return triggers;
    }

    private boolean checkCollision(Entity activeEntity, Entity otherEntity){
        return activeEntity.getBounds().intersects(otherEntity.getBounds());
    }

    private String getCollisionType(Entity activeEntity, Entity otherEntity){

        if(activeEntity.getBounds().getMinY()<=otherEntity.getBounds().getMaxY() &&
                activeEntity.getBounds().getMinY()>otherEntity.getBounds().getMinY() && (activeEntity.getVelocity().getyVelocity()<0)
        ){
            return "TOP";
        } else if(activeEntity.getBounds().getMaxY()>=otherEntity.getBounds().getMinY() &&
                activeEntity.getBounds().getMinY()<otherEntity.getBounds().getMinY() && (activeEntity.getVelocity().getyVelocity()>0 )){

            return "BOTTOM";
        } else if(activeEntity.getBounds().getMinX()<otherEntity.getBounds().getMinX() &&
                activeEntity.getBounds().getMaxX()>=otherEntity.getBounds().getMinX() && activeEntity.getVelocity().getxVelocity()>0){

            return "RIGHT";
        }
        else if (activeEntity.getBounds().getMinX()<otherEntity.getBounds().getMaxX() &&
                    activeEntity.getBounds().getMaxX()>=otherEntity.getBounds().getMaxX() && activeEntity.getVelocity().getxVelocity()<0){
            return "LEFT";

        } else{
            return "NONE";
        }

    }
}
