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
class LivesBindingTest {

    Entity modelEntity;
    Entity viewEntity;
    EntityBinder binder;


    public LivesBindingTest(){
        ParameterController pc = new ParameterController();
        pc.setImage("image address");
        pc.setCoordinate(0,0);
        pc.setHeight(0);

        EntityFactory modelFactory = new EntityFactory();
        modelEntity = modelFactory.getEntity("Player", pc);
        ViewEntityFactory viewFactory = new ViewEntityFactory();
        viewEntity = viewFactory.getEntity("Player", pc);

        binder = new EntityBinder(modelEntity, viewEntity);
    }

    @Test
    void setLives() {
        modelEntity.setLives(10);
        modelEntity.setLives(20);
        assertEquals(20, binder.getMyViewEntity().getEntityGameStatus().getValue().getLives());
    }

    @Test
    void getScoreInfo() {
        assertEquals(binder.getMyBackEndEntity().getEntityGameStatus().getValue().getScoreProfile(),
                        binder.getMyViewEntity().getEntityGameStatus().getValue().getScoreProfile());
    }
}
