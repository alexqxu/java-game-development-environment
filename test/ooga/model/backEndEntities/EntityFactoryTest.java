package ooga.model.backEndEntities;

import ooga.controller.ParameterController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityFactoryTest {

        @Test
        void getEntity() {
            EntityFactory bf=new EntityFactory();
            ParameterController pc= new ParameterController();
            pc.setCoordinate(100,200);
            pc.setImage("image address");
            pc.setInteractions("Enemy","ToCollision","AddLife");

            PlayerEntity playerBackEndEntity=new PlayerEntity(pc);
            ObjectEntity objectEntity= new ObjectEntity(pc);

            assertEquals(playerBackEndEntity.getParameterController().getImage(),
                    bf.getEntity("Player", pc).getParameterController().getImage());
            assertEquals(objectEntity.getParameterController().getImage(),
                    bf.getEntity("Object", pc).getParameterController().getImage());

        }
    }
