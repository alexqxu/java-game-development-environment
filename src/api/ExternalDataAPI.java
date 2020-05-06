/**
 * Provides functionality to read configuration files, assets, and preferences to error check and assemble the
 * correct combination of engine classes
 */

package api;

import ooga.controller.EntityController;
import ooga.controller.WinnerProfile;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public interface ExternalDataAPI {

    /**
     * Return the name of the game currently being played
     * @return the name of the game currently being played
     */
    public String getGameName();

    /**
     * Return the level number of the game currently being played
     * @return the level number of the game currently being played
     */
    public int getGameLevel();

    /**
     * Return the n highest scores from previous sessions of the game currently being played
     * @param n the amount of high scores of the game currently being played
     * @return the n highest scores from previous sessions of the game currently being played in descending order
     */
    public List<Integer> getHighScores(int n);

    /**
     * Return the player names corresponding to the n highest scores from the previous sessions of the game currently
     * being played
     * @param n the amount of player names to be accessed corresponding to the n highest scores of the game currently
     *          being played
     * @return the player names corresponding to the n highest scores from the previous sessions of the game currently
     * being played in descending order of corresponding highest score
     */
    public Collection<WinnerProfile> getWinnerList(int n);

    /**
     * Return the names of the substage configuration files to be loaded in the order that they should be loaded
     * @return the names of the substage configuration files to be loaded in the order that they should be loaded
     */
    public List<String> getOrderOfGame();


    /**
     * Load the corresponding properties file and resource file for the game that will be played
     */
    public void loadGameData();

    /**
     * Return the game configuration XML file directory
     * @return the directory of the game configuration XML file
     */
    public String getGameConfiguration();

    public EntityController getEntityController();



}
