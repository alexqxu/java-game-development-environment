package ooga.controller.keyboardInputHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the finite state machine that defines the keyboard input to the game controller.
 * @author Alex Xu
 */
public class InputStateMachine {
    private KeyList myKeyList;

    /**
     * Constructor for the InputStatemachine
     */
    public InputStateMachine(){
        myKeyList = new KeyList();
    }

    /**
     * Method called when a key is pressed
     * @param key
     */
    public void pressKey(String key){
        if(!myKeyList.contains(key)){
            myKeyList.addKey(key);
        }
    }

    /**
     * Method called when a key is released
     * @param key
     */
    public void releaseKey(String key){
        if(myKeyList.contains(key)) {
            myKeyList.removeKey(key);
        }
    }

    public void releaseAllKeys(){
        myKeyList = new KeyList();
    }

    /**
     * To retrieve the list of currently active keys
     * @return
     */
    public List<String> getActiveKeyList(){
        return myKeyList.toStringList();
    }
}
