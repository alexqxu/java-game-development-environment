package ooga.engine.helperObjects;

import java.util.ResourceBundle;

public class Move {
    private String action;
    private Integer size;
   // private static final String FILE_PATH="resources.engineProperties.MovementReversingMap";
    //private ResourceBundle resourceBundle = java.util.ResourceBundle.getBundle(FILE_PATH);

    /**
     *
     * @param moveName
     * @param value
     */
    public Move(String moveName, Integer value){
        action=moveName;
        size=value;
    }

    /**
     *
     * @return the action to execute like move left and move right
     */
    public String getAction() {
        return action;
    }
    /**
     *
     * @return the size of the movement in the defined direction
     */
    public Integer getSize() {
        return size;
    }

//    public void reverseAction(){
//        this.action=resourceBundle.getString(action);
//    }

}
