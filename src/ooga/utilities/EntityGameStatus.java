package ooga.utilities;

import ooga.controller.WinnerProfile;

public class EntityGameStatus {

    private String myName;
    private String myStatus;
    private int myLives;
    private WinnerProfile myWinnerProfile;

    public EntityGameStatus(String name, String status, int lives, int score){
        myName = name;
        myStatus = status;
        myLives = lives;;
        myWinnerProfile = new WinnerProfile(myName, score);
    }

    public String getStatus() {
        return myStatus;
    }

    public void setStatus(String myStatus) {
        this.myStatus = myStatus;
    }

    public WinnerProfile getScoreProfile() {
        return myWinnerProfile;
    }

    public void setScore(int myScore) {
        myWinnerProfile = new WinnerProfile(myName, myScore);
    }

    public int getScore(){ return myWinnerProfile.getScore(); }

    public int getLives() {
        return myLives;
    }

    public void setLives(int myLives) {
        this.myLives = myLives;
    }


    public boolean isVisibilityisibility(){
        return myLives>0;
    }

}
