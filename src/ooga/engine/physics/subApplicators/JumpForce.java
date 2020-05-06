package ooga.engine.physics.subApplicators;
import ooga.model.Entity;

/**
 * This class moves the entity up when a key is pressed
 * @author  Abeb Amare
 */
public class JumpForce extends AbstractSubApplicator {
    private String[] nameClass=this.getClass().getName().split("[.]");
    private String name=nameClass[nameClass.length-1];
    public JumpForce(Entity entity){
        super(entity);
        applyForce(ForceValues.checkX(name), ForceValues.checkY(name));

    }
}
