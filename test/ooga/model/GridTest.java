package ooga.model;

import ooga.controller.ParameterController;
import ooga.parser.components.GeneralInfoParser;
import ooga.model.backEndEntities.EntityFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void mountEntities() {
        ParameterController parameterController = new ParameterController();
        //GeneralInfoParser generalInfoParser = new GeneralInfoParser("data/SampleGame1/");
        EntityFactory factory = new EntityFactory();
        parameterController.setCoordinate(10,20);
        Entity player = factory.getEntity("Player", parameterController);
        Grid grid = new Grid(null);
        grid.mountEntities(player);
        assertEquals(grid.getGrid(10,20),player);
    }
}