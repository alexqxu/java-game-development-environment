package ooga.parser.helperObjects;

/**
 * This encapsulates interactions.
 * @author Alex Xu
 */
public class Interaction {
    private String myObject;
    private String mySide;
    private String myInteractionName;

    public Interaction(String object, String side, String interactionName){
        myObject = object;
        mySide = side;
        myInteractionName = interactionName;
    }

    public String getMyObject(){
        return myObject;
    }

    public String getSide(){
        return mySide;
    }

    public String getInteractionName(){
        return myInteractionName;
    }
}
