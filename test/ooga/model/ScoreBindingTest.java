package ooga.model;

import ooga.controller.EntityBinder;
import ooga.controller.ParameterController;
import ooga.model.backEndEntities.EntityFactory;
import ooga.view.viewEntities.ViewEntityFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Achilles D.
 * Tests binding of score information in model-view Entity pairs
 */
class ScoreBindingTest {

    private Entity modelEntity;
    private Entity viewEntity;
    private EntityBinder binder;
    private static final String PLAYER_ENTITY_OPTION = "Player";
    private static final String ENTITY_NAME = "P1";
    private static final int INIT_SCORE = 10;
    private static final int NEW_SCORE = 20;


    public ScoreBindingTest(){
        ParameterController pc = new ParameterController();
        pc.setName(ENTITY_NAME);

        EntityFactory modelFactory = new EntityFactory();
        modelEntity = modelFactory.getEntity(PLAYER_ENTITY_OPTION, pc);
        ViewEntityFactory viewFactory = new ViewEntityFactory();
        viewEntity = viewFactory.getEntity(PLAYER_ENTITY_OPTION, pc);

        binder = new EntityBinder(modelEntity, viewEntity);
    }

    @Test
    void setScore() {
        modelEntity.setScore(INIT_SCORE);
        modelEntity.setScore(NEW_SCORE);
        assertEquals(NEW_SCORE, binder.getMyViewEntity().getEntityGameStatus().getValue().getScore());
    }

    @Test
    void getScoreInfo() {
        assertEquals(binder.getMyBackEndEntity().getEntityGameStatus().getValue().getScoreProfile().getName(),
                binder.getMyViewEntity().getEntityGameStatus().getValue().getScoreProfile().getName());
    }
}