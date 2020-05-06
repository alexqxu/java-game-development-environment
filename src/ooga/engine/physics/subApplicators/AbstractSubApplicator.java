package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This abstract class applies force to the entity
 */
public abstract class AbstractSubApplicator {
    protected Entity myEntity;

    public AbstractSubApplicator(Entity entity){
        myEntity = entity;
    }

    public void applyForce(double xForce, double yForce){
        double newXForce = xForce + myEntity.getForce().getFX();
        double newYForce = yForce +  myEntity.getForce().getFY();
        myEntity.setForce(newXForce,newYForce);
    }
}
