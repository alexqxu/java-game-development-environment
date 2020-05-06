package api;

/**
 * Provides functionality to support a computer-controlled character that a human player can play against in the game
 */
public interface BotAPI {

    /**
     * Execute the next gameplay action for the computer-controlled character
     */
    public void executeNextAction(Runnable action);

    /**
     * Reduce the computer-controlled character's health by a specified amount
     * @param delta the amount by which the computer-controlled character's health must decrease
     */
    public void reduceHealth(int delta);

}
