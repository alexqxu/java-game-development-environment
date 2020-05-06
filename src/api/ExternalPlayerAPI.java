package api;

/**
 * Provides functionality to allow for the the frontend to collect information about the player's various choices
 * made in regard to game and level selected from the backend so that it can update appropriately 
 */
public interface ExternalPlayerAPI {

    /**
     * Set the game to be played to the game selected by the player in the UI
     */
    public void setGameSelected();

    /**
     * Set the game level to be played to the level selected by the player in the UI
     */
    public void setGameLevelSelected();

    /**
     * Updates the game selector carousel to display the last played game at the beginning of the list
     */
    public void updateLibraryView();
}
