package ooga.controller;

import ooga.model.Entity;

public class EntityBinder {

    private Entity myViewEntity;

    private Entity myBackEndEntity;

    /**
     *
     * @param view the view entity
     * @param backend the backend entity to be bind with the view entity
     */
    public EntityBinder(Entity view, Entity backend)
    {
        myBackEndEntity = backend;
        myViewEntity = view;
        connectEntities();
    }

    /**
     * binds the front and the back end entity
     *
     */
    private void connectEntities()
    {
        myBackEndEntity.getSimpleEntityProperty().bindBidirectional(myViewEntity.getSimpleEntityProperty());
        myBackEndEntity.getDynamicEntityCoordinate().bindBidirectional(myViewEntity.getDynamicEntityCoordinate());
        myBackEndEntity.getEntityGameStatus().bindBidirectional(myViewEntity.getEntityGameStatus());
    }

    /**
     *
     * @return the viewEntity
     */
    public Entity getMyViewEntity()
    {
        return myViewEntity;
    }

    /**
     *
     * @return the backEnd entity
     */
    public Entity getMyBackEndEntity()
    {
        return myBackEndEntity;
    }
}

