package ooga.controller;


public class WinnerProfile {

    private int score;
    private String name;

    /**
     * This class is used to handle the profile of a player like score, life
     * @param name the name of the player
     * @param score the score of the player
     */
    public WinnerProfile(String name, int score){
        this.name=name;
        this.score=score;
    }

    /**
     *
     * @return the score of the player
     */
    public int getScore(){
        return score;
    }

    /**
     *
     * @return the name of the player
     */
    public String getName(){
        return name;
    }

}
