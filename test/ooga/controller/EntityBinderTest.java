package ooga.controller;

import ooga.model.Entity;
import ooga.model.backEndEntities.EntityFactory;
import ooga.model.backEndEntities.PlayerEntity;
import ooga.view.viewEntities.ViewEntityFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityBinderTest {



    //PlayerEntity pe=new PlayerEntity("Player");
    private Entity pe;
    private Entity vpe;
    private EntityBinder eb;

    public EntityBinderTest(){
        ParameterController par=new ParameterController();
        par.setImage("image adress");
        par.setCoordinate(20,40);
        par.setHeight(20);
        EntityFactory ef=new EntityFactory();
        ViewEntityFactory vef=new ViewEntityFactory();

        pe=ef.getEntity("Player", par);
        vpe=vef.getEntity("Player", par);
         eb=new EntityBinder(pe,vpe);

    }



    @Test
    void getMyViewEntity() {
        assertEquals(pe,eb.getMyViewEntity());
    }

    @Test
    void getMyBackEndEntity() {
        assertEquals(vpe,eb.getMyBackEndEntity());
    }
}