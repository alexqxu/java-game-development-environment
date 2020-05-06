package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * This class moves the entity down
 * @author  Abebe Amare
 */
public class MoveDownForce extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];

    public MoveDownForce(Entity entity){
        super(entity);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));
    }
}
