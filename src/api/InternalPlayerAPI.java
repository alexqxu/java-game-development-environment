/**
 * program that loads the game data and uses the game engine to run a particular game
 */
package api;

/**
 * Provides functionality to allow the backend to move players' characters and change their health and score
 */
public interface InternalPlayerAPI {

    /**
     * Move the player's character a specified amount of pixels to the left
     * @param amount the amount of pixels to move to the left
     */
    public void moveLeft(int amount);

    /**
     * Move the player's character a specified amount of pixels to the right
     * @param amount the amount of pixels to move to the right
     */
    public void moveRight(int amount);

    /**
     * Move the player's character a specified amount of pixels up
     * @param amount the amount of pixels to move to the up
     */
    public void moveUp(int amount);

    /**
     * Move the player's character diagonally
     * @param angle the angle to for the character to move where zero degrees is defined as along the plane
     *              which the player's character is facing
     * @param amount the amount of pixels to move the player's character at the specified angle
     */
    public void move(double angle, int amount);

    /**
     * Set the player's character health to a specified value
     * @param health the new health of the player's character
     */
    public void setLives(int health);

    /**
     * Set the player's score to a specified value
     * @param score the new score for the player
     */
    public void setScore(int score);

    /**
     *
     * @return the status of the entity
     */
    public String getStatus();

}
