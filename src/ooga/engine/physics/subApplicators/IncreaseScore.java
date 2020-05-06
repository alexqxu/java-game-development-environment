package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

/**
 * A subApplicator to change an Entity's score as a result of a game event
 * @author Achilles D.
 */
public class IncreaseScore extends AbstractSubApplicator{

    private static final int SCORE_INCREMENT = 1;

    public IncreaseScore(Entity entity){
        super(entity);
        myEntity.setScore(entity.getEntityGameStatus().getValue().getScore() + SCORE_INCREMENT);

    }

}
