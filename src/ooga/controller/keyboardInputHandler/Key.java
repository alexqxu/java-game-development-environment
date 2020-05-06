package ooga.controller.keyboardInputHandler;

/**
 * Encapsulates a keyinput
 * @author Alex Xu
 */
public class Key {
    private String keyValue;

    public Key(String value){
        keyValue = value;
    }

    public String getValue(){
        return keyValue;
    }

    public boolean equals(Key compareKey){
        return compareKey.getValue().equals(this.getValue());
    }
}
