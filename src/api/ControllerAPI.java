package api;

/**
 * Provides functionality for a controller class that will act as the main interface between the frontend and backend
 * and check for display events, inciting new events on the display (facilitated by the backend) as a result of the
 * display events that just took place
 */
public interface ControllerAPI {

    /**
     * Begin gameplay of the selected game
     */
    public void playGame();

    /**
     * Display the next frame on the gameplay window
     */

    /**
     * Check for collisions between game entities
     */

    /**
     * Display VFX and play SFX corresponding to an in-game event
     */
    public void applyEffects();

    /**
     * Apply in-game physics to game entities
     */
    public void applyPhysics();
}
