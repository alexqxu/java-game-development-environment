package ooga.engine;

import ooga.engine.boundaryHandler.BoundaryHandler;
import ooga.engine.physics.ActionApplicator;
import ooga.engine.physics.physicsCalculator.PhysicsCalculator;
import ooga.engine.triggerHandler.GeneralTriggerHandler;
import ooga.model.Entity;

import java.util.Collections;
import java.util.List;

/**
 * Main engine class
 */
public class Engine {
    private List<Entity> myBackEndEntities;
    private GeneralTriggerHandler myGeneralTriggerHandler;
    private ActionApplicator myActionApplicator;
    private PhysicsCalculator myPhysicsCalculator;

    //TODO: Maybe Collection<Entity>
    public Engine(List<Entity> backEndEntities, String gameFolderPath, int levelNumber){
        myBackEndEntities = backEndEntities;
        myGeneralTriggerHandler = new GeneralTriggerHandler(Collections.unmodifiableList(backEndEntities), gameFolderPath);
        myPhysicsCalculator = new PhysicsCalculator(myBackEndEntities, gameFolderPath, levelNumber);
    }

    public void update(List<String> keyInput){
        myActionApplicator = new ActionApplicator(myGeneralTriggerHandler.getAllTriggers(keyInput));
        myPhysicsCalculator.update();
    }

    //TODO: May not be needed at all
    public void reset(){

    }
}
