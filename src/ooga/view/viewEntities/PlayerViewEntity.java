package ooga.view.viewEntities;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.model.backEndEntities.EntityValues;
import ooga.utilities.EntityGameStatus;

import java.util.List;

public class PlayerViewEntity extends Entity {

    private SimpleStringProperty myVictoryStatus;

    /**
     * The main playing character
     * @param pc parameter controller that has all the parameters that the character needs
     */
    private static final String name="Player";
    public PlayerViewEntity(ParameterController pc){
       super(pc);
        myGameStatus = new SimpleObjectProperty<>(new EntityGameStatus(getName(), EntityValues.getStatus(name),
                EntityValues.getScore(name),EntityValues.getScore(name)));
    }

    public void setLives(int lives) {
        myGameStatus.getValue().setLives(lives);
    }

    public void setScore(int score) {
        myGameStatus.getValue().setScore(score);
    }
    public String getStatus(){
        return myGameStatus.getValue().getStatus();
    }
}
