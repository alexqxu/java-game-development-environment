package api;

import javax.swing.*;
import java.util.List;

/**
 * Provides functionality to support accessing entity images, player control keybindings, the number of players
 * in the current game, and the jump height of player characters
 */
public interface InternalDataAPI {

    /**
     * Return the keybindings for controlling the character in the game
     * @return the keybindings for controlling the character in the game
     */
    public InputMap getKeys();

    /**
     * Return the directories for the images corresponding to in-game power-ups
     * @return the directories for the images corresponding to in-game power-ups
     */
    public List<String> getPowerUpImages();

    /**
     * Return the directory for the image corresponding to bricks displayed on the stage
     * @return the directory for the image corresponding to bricks displayed on the stage
     */
    public String getBrickImage();

    /**
     * Return the directory for the image corresponding to warp pipes displayed on the stage
     * @return the directory for the image corresponding to warp pipes displayed on the stage
     */
    public String getTubeImage();

    /**
     * Return the directory for the image corresponding to stairways displayed on the stage
     * @return the directory for the image corresponding to stairways displayed on the stage
     */
    public String getStairsImage();

    /**
     * Return the directory for the image corresponding to the clouds displayed in the stage
     * @return the directory for the image corresponding to the clouds displayed in the stage
     */
    public String getCloudImage();

    /**
     * Return the number of people currently playing the game
     * @return the number of people currently playing the game
     */
    public int getNumberOfPlayers();

    /**
     * Return the amount of pixels that the player's character moves vertically when the character jumps
     * @return the amount of pixels that the player's character moves vertically when the character jumps
     */
    public int getPlayerJumpHeight();
}
