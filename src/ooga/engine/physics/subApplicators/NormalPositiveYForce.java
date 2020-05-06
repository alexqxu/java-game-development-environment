package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This class applies a normal force to the player entity and acts like anti-gravity
 * @author  Abebe Amare
 */
public class NormalPositiveYForce extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];
    public NormalPositiveYForce(Entity entity){
        super(entity);
        entity.setVelocity(0,0);
        entity.setAcceleration(0,0);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));

    }
}
