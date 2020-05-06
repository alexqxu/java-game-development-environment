package ooga.model.backEndEntities;

import javafx.beans.property.SimpleObjectProperty;
import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.utilities.EntityGameStatus;

public class ObjectEntity extends Entity {

    /**
     *
     * @param par parameter controller which has all the arguments/values that the brick has
     */

    private static final String name="Object";

    public ObjectEntity(ParameterController par) {
        super(par);
        setFalling(false);
        myGameStatus = new SimpleObjectProperty<>(new EntityGameStatus(getName(), EntityValues.getStatus(name),
                EntityValues.getScore(name),
                EntityValues.getLive(name)));
    }


    public void setLives(int lives) {
        EntityGameStatus oldStatus=myGameStatus.getValue();
        String status=EntityValues.dead();
        if(lives>0){
            status=EntityValues.playing();
        }
        myGameStatus.setValue(new EntityGameStatus(getName(), status,lives,
                oldStatus.getScore()));

    }

    public void setScore(int score) {

        EntityGameStatus oldStatus=myGameStatus.getValue();
        myGameStatus.setValue(new EntityGameStatus(getName(), oldStatus.getStatus(), oldStatus.getLives(), score));
    }
    public String getStatus(){
        return myGameStatus.getValue().getStatus();
    }

}
