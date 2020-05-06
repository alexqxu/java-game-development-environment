package ooga.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Achilles D.
 */
public class InteractionBinder {

    private Map<String, List<String>> myInteractionBindings;

    public InteractionBinder(){
        myInteractionBindings = new HashMap<>();
    }

    public void addBinding(String interactionType, String method){
       myInteractionBindings.putIfAbsent(interactionType, new ArrayList<>());
        myInteractionBindings.get(interactionType).add(method);
    }

    public List<String> getMethods(String interactionType){
        return myInteractionBindings.get(interactionType);
    }

}
