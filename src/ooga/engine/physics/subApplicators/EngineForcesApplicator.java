package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This class applies Gravity to the entity
 * @author  Abebe Amare
 */
public class EngineForcesApplicator extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];
    public EngineForcesApplicator(Entity entity) {
        super(entity);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));

    }
}
