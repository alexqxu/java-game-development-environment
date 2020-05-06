package ooga.engine.physics.physicsCalculator;

import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.model.backEndEntities.EntityFactory;
import ooga.model.backEndEntities.PlayerEntity;
import ooga.parser.components.GeneralInfoParser;
import ooga.parser.components.LevelInfoParser;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsCalculatorBugTest extends DukeApplicationTest {

    private static final String PLAYER_ENTITY_OPTION = "Player";
    private static final String ENTITY_NAME = "Player 1";
    private static final int DEFAULT_INIT_VALUE = 0;
    private static final int INIT_X_COORD = 1;
    private static final int INIT_Y_COORD = 550;
    private static final int INIT_ACCEL = 10;
    private static final int WRONG_FINAL_ACCEL = 0;
    private static final String GAME_FOLDER_PATH = "data/Games/Bounce/";
    private static final int LEVEL_NUMBER = 1;
    private List<Entity> myEntities;

    public PhysicsCalculatorBugTest(){
        ParameterController pc = new ParameterController();
        pc.setName(ENTITY_NAME);
        EntityFactory modelFactory = new EntityFactory();
        myEntities = new ArrayList<>();
        myEntities.add(modelFactory.getEntity(PLAYER_ENTITY_OPTION, pc));
    }

    @Test
    void update(){
        Entity player = myEntities.get(DEFAULT_INIT_VALUE);
        player.setCoordinate(INIT_X_COORD,INIT_Y_COORD);
        player.setAcceleration(INIT_ACCEL,INIT_ACCEL);
        PhysicsCalculator physCalc = new PhysicsCalculator(myEntities, GAME_FOLDER_PATH, LEVEL_NUMBER);
        physCalc.update();
        assertNotEquals(WRONG_FINAL_ACCEL, player.getAcceleration().getYAcceleration());
    }

}