package ooga.engine.helperObjects;

import ooga.utilities.Force;

public class Action {
    private String myAction;
    private Force force;

    public Action(String action){
        myAction = action;
        force = new Force(0,0);
    }

    //TODO: getter

    public String getAction(){
        return myAction;
    }

    public Force getForce(){ return force; }

    public void setForce(double xForce, double yForce){
       force= new Force(xForce, yForce);
    }

}
