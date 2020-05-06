package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This class moves the entity to the left
 * @author  Abebe Amare
 */
public class MoveLeftForce extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];
    private static final String FORCE_TYPE="Hit";

    public MoveLeftForce(Entity entity){
        super(entity);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));
        entity.getForce().setForceType(FORCE_TYPE);


    }
}
