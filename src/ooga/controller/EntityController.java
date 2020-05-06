package ooga.controller;

import ooga.parser.frontFacingParsers.EntityParser;
import ooga.model.Entity;
import ooga.model.backEndEntities.EntityFactory;
import ooga.view.viewEntities.ViewEntityFactory;

import java.util.*;


public class EntityController {

    private Map<String, EntityBinder> entitiesMap;
    private ViewEntityFactory viewEntityFactory;
    private EntityFactory entityFactory;
    private EntityParser entityParser;

    /**
     * This class binds back and view entities and has getter methods to access entities that are bind
     *  Has access to all the entities created in the game
     */
    public EntityController(int level, String folderPath) {
        entitiesMap = new HashMap<>();
        entityFactory = new EntityFactory();
        viewEntityFactory = new ViewEntityFactory();
        entityParser=new EntityParser((folderPath));
        entityParser.setLevel(level,folderPath);
        createNewLink();
    }

    /**
     * it loops through all the entities in the map, create the front and the backend entities and bind them
     */
    private void createNewLink() {

        for (String str : entityParser.getParameters().keySet()) {
            String objectType=str.split("_")[0]; // edit it later

            Entity backEndEntity = entityFactory.getEntity(objectType, entityParser.getParameters().get(str));
            Entity viewEntity = viewEntityFactory.getEntity(objectType, entityParser.getParameters().get(str));
            EntityBinder entityBinder = new EntityBinder(viewEntity, backEndEntity);
            entitiesMap.put(str, entityBinder);
        }
    }

    /**
     *
     * @return all the backend entities as a collection
     */
    public Collection<Entity> getAllBackEndEntities() {

        List<Entity> listBackEndEntity= new ArrayList<>();
        for(EntityBinder binder: entitiesMap.values()){
            listBackEndEntity.add(binder.getMyBackEndEntity());
        }
        return listBackEndEntity;

    }

    /**
     *
     * @return all the view entities as a collection
     */
    public Collection<Entity> getAllViewEntities() {

        List<Entity> listViewEntity= new ArrayList<>();
        for(EntityBinder binder: entitiesMap.values()){
            listViewEntity.add(binder.getMyViewEntity());
        }
        return listViewEntity;

    }

}
