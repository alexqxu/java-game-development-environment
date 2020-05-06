package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

public class SetGameOver extends AbstractSubApplicator{
    public SetGameOver(Entity entity){
        super(entity);
        myEntity.setStatus("game over");
    }
}
