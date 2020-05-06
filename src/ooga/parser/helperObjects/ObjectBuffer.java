package ooga.parser.helperObjects;

import ooga.utilities.exception.NoSuchGameObjectException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The purpose of this class is to encapsulate the object buffer, to help the parser understand what objects
 * are available. In other words, a wrapper class for a data structure that holds GameObjects. Uses composition.
 * @author Alex Xu
 */
public class ObjectBuffer implements Iterable{
    private List<GameObject> myList;

    public ObjectBuffer(){
        myList = new ArrayList<>();
    }

    public void add(GameObject gameObject){
        myList.add(gameObject);
    }

    public GameObject get(int index){
        return myList.get(index);
    }

    public GameObject get(String name){
        for(GameObject entry : myList){
            if(entry.getName().equals(name)){
                return entry;
            }
        }
        throw new NoSuchGameObjectException();
    }

    public int size(){
        return myList.size();
    }

    @Override
    public Iterator iterator() {
        return myList.iterator();
    }
}