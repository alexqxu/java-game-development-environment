package ooga.parser.helperObjects;

import java.util.List;

/**
 * This helper object encapsulates a Character.
 * @author Alex Xu
 */
public class Character {
    private String myName;
    private String myImageFilepath;
    private int myX;
    private int myY;
    private int myWidth;
    private int myHeight;
    private List<Interaction> myInteractions;
    private List<Control> myControls;

    public Character(String name, String filePath, int x, int y, int width, int height, List<Interaction> interactions, List<Control> controls){
        myName = name;
        myImageFilepath = filePath;
        myX = x;
        myY = y;
        myWidth = width;
        myHeight = height;
        myInteractions = interactions;
        myControls = controls;
    }

    public String getName(){
        return myName;
    }
    public String getImagePath(){
        return myImageFilepath;
    }

    public int getX(){
        return myX;
    }

    public int getY(){
        return myY;
    }

    public int getWidth(){
        return myWidth;
    }

    public int getHeight(){
        return myHeight;
    }

    public List<Interaction> getInteractions(){
        return myInteractions;
    }

    public List<Control> getControls(){
        return myControls;
    }

    @Override
    public boolean equals(Object object){
        Character character = (Character) object;
        return character.getName().equals(this.getName());
    }
}
