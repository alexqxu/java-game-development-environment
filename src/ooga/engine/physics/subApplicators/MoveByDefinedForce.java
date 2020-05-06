package ooga.engine.physics.subApplicators;

import ooga.model.Entity;
import ooga.utilities.Force;

/**
 * This class is used by the enemy and the entity will be moved by the force defined in the constructor
 * @author  Abebe Amare
 */
public class MoveByDefinedForce extends AbstractSubApplicator{

    public MoveByDefinedForce(Entity entity, Force force){
        super(entity);
        entity.setAcceleration(0,0);
        entity.setVelocity(0,0);
        applyForce(force.getFX(), force.getFY());

    }

}
