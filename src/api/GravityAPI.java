package api;

/**
 * Provides functionality to support gravity game physics for moving game entities
 */
public interface GravityAPI {

    /**
     * Enable gravity during gameplay for all moving entities
     */
    public void enableGravity();

    /**
     * Disable gravity during gameplay for all moving entities
     */
    public void disableGravity();

    /**
     * Set gravity to pull entities towards the top of the gameplay window if it is currently pulling entities
     * toward the bottom of the gameplay window and vice versa
     */
    public void invertGravity();

    /**
     * Return the current intensity of the gravity
     * @return the current intensity of gravity, where larger integers represent higher gravity intensity and vice versa
     */
    public int getGravity();

    /**
     * Set the gravity intensity to a specified value
     * @param intensity
     */
    public void setGravity(int intensity);
}
