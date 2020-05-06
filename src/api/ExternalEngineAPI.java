/**
 * framework of general classes to support any kind of game within the genre
 */
package api;

/**
 * Provides functionality for the frontend to collect information about changes in the state of game
 * from the backend so that the former can update itself appropriately
 */
public interface ExternalEngineAPI {

    /**
     * Return a boolean representing whether the game is still in progress
     * @return false if the game is still in progress; true otherwise
     */
    public boolean isGameFinished();

    /**
     * Return the final score of the player once the game is no longer in progress
     * @return the final score of the player once the game is no longer in progress
     */
    public int getFinalScore();

    /**
     * Reinitialize the current game for future gameplay
     */
    public void resetGame();

    /**
     * Begins gameplay of the game selected by the player
     */
    public void startGame();

    /**
     * Select the game to be played
     * @param gameName the name of the game to be played
     */
    public void setGame(String gameName);

}
