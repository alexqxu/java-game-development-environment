package ooga.controller.bot;

import java.util.ResourceBundle;

public class GhostReader {


    private static final String FILE_PATH="ooga.model.resources.storedmoves";
    private static final ResourceBundle SAVED_INPUTS = java.util.ResourceBundle.getBundle(FILE_PATH);
    private String lastAction;

    public GhostReader(String file){
    }

    public String readVirtualKeyPress(double time){
        if (SAVED_INPUTS.containsKey(String.valueOf(time))){
            setLastAction(SAVED_INPUTS.getString(String.valueOf(time)));
            return SAVED_INPUTS.getString(String.valueOf(time));
        }
        return "null";

    }

    public String getLastAction() {
        return lastAction;
    }

    private void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

}
