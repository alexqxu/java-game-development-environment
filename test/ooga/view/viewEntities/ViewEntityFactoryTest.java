package ooga.view.viewEntities;

import ooga.controller.ParameterController;
import ooga.model.backEndEntities.EntityFactory;
import ooga.model.backEndEntities.ObjectEntity;
import ooga.model.backEndEntities.PlayerEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewEntityFactoryTest {

    @Test
    void getEntity() {
        ViewEntityFactory vf=new ViewEntityFactory();

        assertEquals(PlayerViewEntity.class,vf.getEntity("Player", new ParameterController()).getClass());

        ViewEntityFactory bf=new ViewEntityFactory();
        ParameterController pc= new ParameterController();
        pc.setCoordinate(100,200);
        pc.setImage("image address");
        pc.setInteractions("Enemy","ToCollision","AddLife");

        PlayerViewEntity playerBackEndEntity=new PlayerViewEntity(pc);
        ObjectEntity objectEntity= new ObjectEntity(pc);

        assertEquals(playerBackEndEntity.getParameterController().getImage(),
                bf.getEntity("Player", pc).getParameterController().getImage());
        assertEquals(objectEntity.getParameterController().getImage(),
                bf.getEntity("Object", pc).getParameterController().getImage());

    }
}