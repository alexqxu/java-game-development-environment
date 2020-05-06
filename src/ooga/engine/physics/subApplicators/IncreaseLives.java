package ooga.engine.physics.subApplicators;

        import ooga.model.Entity;

/**
 * A subApplicator to change an Entity's amount of remaining lives as a result of a game event
 * @author Achilles D.
 */
public class IncreaseLives extends AbstractSubApplicator {

    private static final int LIVES_INCREMENT = 1;

    public IncreaseLives(Entity entity){
        super(entity);
        entity.setLives(entity.getEntityGameStatus().getValue().getLives() + LIVES_INCREMENT);
    }
}
