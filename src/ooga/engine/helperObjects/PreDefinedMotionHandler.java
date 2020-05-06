package ooga.engine.helperObjects;

import java.util.LinkedList;

public class PreDefinedMotionHandler {

    private LinkedList<Move> predefinedMotion;

    public PreDefinedMotionHandler(){
        predefinedMotion=new LinkedList<>();
    }

    public void addAction(String name, Integer value){
        for (int i = 0; i<value/2; i++){
            predefinedMotion.add(new Move(name, 2));
        }

    }

    public Move getCurrentAction(){
        Move current=predefinedMotion.poll();
        predefinedMotion.add(current);
        return current;
    }

    public int getSizeOfActions(){
        return predefinedMotion.size();
    }



}
