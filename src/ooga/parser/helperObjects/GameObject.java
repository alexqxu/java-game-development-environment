package ooga.parser.helperObjects;

import java.util.List;

/**
 * Encapsulates data that is involved for an object in the Game.
 * @author Alex Xu
 */
public class GameObject {
    private String myName;
    private String myImagePath;
    private int myWidth;
    private int myHeight;
    private List<Interaction> myInteractions; //TODO: Create a wrapper class for Interactions List.
    private List<Movement> myMovements;

    public GameObject(String name, String imagePath, int width, int height, List<Interaction> interactions, List<Movement> movements){
        myName = name;
        myImagePath = imagePath;
        myWidth = width;
        myHeight = height;
        myInteractions = interactions;
        myMovements = movements;
    }

    public String getImagePath(){
        return myImagePath;
    }

    public int getWidth(){
        return myWidth;
    }

    public int getHeight(){
        return myHeight;
    }

    public String getName(){
        return myName;
    }

    public List<Interaction> getInteractions(){
        return myInteractions;
    }

    public List<Movement> getMovements(){
        return myMovements;
    }
}