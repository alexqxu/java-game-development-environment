package ooga.engine.physics.physicsCalculator;

import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.model.backEndEntities.PlayerEntity;
import ooga.parser.components.GeneralInfoParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhysicsCalculatorTest {

    private static final String GAME_FOLDER_PATH = "data/Games/Doodle/level_1.xml";
    private static final int LEVEL_NUMBER = 1;
    private List<Entity> entityList=new ArrayList<>();
    private ParameterController pc=new ParameterController();
    private PlayerEntity playerEntity=new PlayerEntity(pc);


    @Test
    void update() {
        playerEntity.setCoordinate(10,12);
        playerEntity.setForce(10,20);
        playerEntity.setAcceleration(5,10);
        playerEntity.setVelocity(20,20);
        entityList.add(playerEntity);
        PhysicsCalculator physicsCalculator=new PhysicsCalculator(entityList, GAME_FOLDER_PATH, LEVEL_NUMBER);
        physicsCalculator.update();

        assertEquals(37, entityList.get(0).getCoordinate().getX());
        assertEquals(47, entityList.get(0).getCoordinate().getY());
    }
}