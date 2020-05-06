package ooga.engine.physics.subApplicators;

import ooga.model.Entity;

public class SetStageClear extends AbstractSubApplicator{
    public SetStageClear(Entity entity){
        super(entity);
        myEntity.setStatus("game over");
    }
}
