package ooga.parser.helperObjects;

/**
 * This is a helper class that encapsulates a Movement.
 * @author Alex Xu
 */
public class Movement {
    private String myName;
    private int myDistance;

    public Movement(String name, int distance){
        myName = name;
        myDistance = distance;
    }

    public String getName(){
        return myName;
    }

    public int getDistance(){
        return myDistance;
    }
}
