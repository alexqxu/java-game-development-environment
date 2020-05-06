package ooga.parser.helperObjects;

/**
 * This helper object encapsulates a Control
 * @author Alex Xu
 */
public class Control {
    private String myKey;
    private String myMethod;

    public Control(String key, String method){
        myKey = key;
        myMethod = method;
    }

    public String getKey(){
        return myKey;
    }

    public String getMethod(){
        return myMethod;
    }
}
