package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This class moves the entity up when a key is pressed
 * @author  Abebe Amare
 */
public class MoveUpForce extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];

    public MoveUpForce(Entity entity){
        super(entity);
        entity.setFalling(true);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));
    }
}
