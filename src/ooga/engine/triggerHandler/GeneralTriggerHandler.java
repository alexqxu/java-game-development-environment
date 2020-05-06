package ooga.engine.triggerHandler;

import ooga.engine.helperObjects.Trigger;
import ooga.model.Entity;
import ooga.model.backEndEntities.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to compile a list of the Triggers.
 * @author Alex Xu, Achilles Dabrowski
 */
public class GeneralTriggerHandler {

    private CollisionTriggerHandler collisionTriggerHandler;
    private KeyInputTriggerHandler keyInputTriggerHandler;
    private EngineForcesTriggerHandler engineForcesTriggerHandler;
    private EnemyTriggerHandler enemyTriggerHandler;
    private EntityStatusTriggerHandler entityStatusTriggerHandler;
    private List<Entity> myEntities;
    private String myLevelFileDir;

    public GeneralTriggerHandler(List<Entity> entities, String levelDir){
        collisionTriggerHandler = new CollisionTriggerHandler();
        keyInputTriggerHandler = new KeyInputTriggerHandler();
        engineForcesTriggerHandler = new EngineForcesTriggerHandler();
        enemyTriggerHandler=new EnemyTriggerHandler();
        entityStatusTriggerHandler = new EntityStatusTriggerHandler();
        myEntities = entities;
        myLevelFileDir = levelDir;

    }

    public List<Trigger> getAllTriggers(List<String> keyInput){
        List<Trigger> allTriggers = new ArrayList<>();

       List<Trigger> collisionTriggers = collisionTriggerHandler.getTriggers(myEntities);
        List<Trigger> keyInputTriggers = keyInputTriggerHandler.getTriggers(myEntities,keyInput);
        List<Trigger> engineForceTriggers = engineForcesTriggerHandler.getTriggers(myEntities);
       List<Trigger> enemyTriggers=enemyTriggerHandler.getTriggers(myEntities);
       List<Trigger> entityStatusTriggers = entityStatusTriggerHandler.getTriggers(getPlayerEntities(), myLevelFileDir);

        allTriggers.addAll(keyInputTriggers);
       allTriggers.addAll(engineForceTriggers);
        allTriggers.addAll(collisionTriggers);
       allTriggers.addAll(enemyTriggers);
       allTriggers.addAll(entityStatusTriggers);

        return allTriggers;
    }

    private List<PlayerEntity> getPlayerEntities(){
        List<PlayerEntity> playerEntities = new ArrayList<PlayerEntity>();
        for(Entity entity : myEntities){
            if(entity.getName().contains("player")){
                playerEntities.add( (PlayerEntity) entity );
            }
        }

        return playerEntities;
    }

}
