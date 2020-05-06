package ooga.controller.keyboardInputHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class abstracts a List of keys.
 */
public class KeyList {
    private List<Key> myKeyList;

    public KeyList(){
        myKeyList = new ArrayList<>();
    }

    public boolean contains(String keyString){
        int index = indexOf(keyString);
        if(index>=0){
            return true;
        }
        else{
            return false;
        }
    }

    public void addKey(String keyString){
        myKeyList.add(new Key(keyString));
    }

    public void removeKey(String keyString){
        int index = indexOf(keyString);
        myKeyList.remove(index);
    }

    public List<String> toStringList(){
        List<String> returnList = new ArrayList<>();
        for(Key listKey: myKeyList){
            returnList.add(listKey.getValue());
        }
        return returnList;
    }

    private int indexOf(String keyString){
        int counter = 0;
        Key myKey = new Key(keyString);
        for(Key listKey: myKeyList){
            if(listKey.equals(myKey)){
                return counter;
            }
            counter++;
        }
        return -1;      //TODO: Remove magic number
    }
}
