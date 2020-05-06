package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This class does exerts 0 forces to the entities
 * @author  Abebe Amare
 */
public class NoneApplicator extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];
    public NoneApplicator(Entity entity) {
        super(entity);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));
    }
}
