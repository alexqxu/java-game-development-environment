package ooga.engine.triggerHandler;

import ooga.engine.helperObjects.Action;
import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;
import ooga.model.backEndEntities.PlayerEntity;
import ooga.parser.components.GeneralInfoParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EntityStatusTriggerHandler extends TriggerHandler{

    public List<Trigger> getTriggers(List<PlayerEntity> players, String currentGameFileDir){
        List<Trigger> statusTriggers = new ArrayList<>();
        GeneralInfoParser scoreThresholdFinder = new GeneralInfoParser(currentGameFileDir);
        int scoreMaxThreshold = scoreThresholdFinder.getThreshold();

        for(PlayerEntity player : players){
            if(player.getEntityGameStatus().getValue().getScore() >= scoreMaxThreshold){
                statusTriggers.add(new Trigger(player, "GameClear"));
            }
        }

        return statusTriggers;

    }
}
