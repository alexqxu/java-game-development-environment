package ooga.model.backEndEntities;

import javafx.beans.property.SimpleObjectProperty;
import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.utilities.EntityGameStatus;

public class PlayerEntity extends Entity{

    /**
     * The main playing character
     * @param pc parameter controller that has all the parameters that the character needs
     */

    private static final String name="Player";
    public PlayerEntity(ParameterController pc)
    {
       super(pc);
        myGameStatus = new SimpleObjectProperty<>(new EntityGameStatus(getName(), EntityValues.getStatus(name),
                EntityValues.getScore(name),
                EntityValues.getLive(name)));    }

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
