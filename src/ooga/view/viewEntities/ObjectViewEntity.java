package ooga.view.viewEntities;

import javafx.beans.property.SimpleObjectProperty;
import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.model.backEndEntities.EntityValues;
import ooga.utilities.EntityGameStatus;

public class ObjectViewEntity extends Entity {

    /**
     * @param pc parameter controller that has all the values/parameters that a specific entity needs
     */

    private static final String name="Object";
    public ObjectViewEntity(ParameterController pc) {
        super(pc);
        myGameStatus = new SimpleObjectProperty<>(new EntityGameStatus(getName(), EntityValues.getStatus(name),
                EntityValues.getLive(name),
                EntityValues.getScore(name)));
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
