package api;

/**
 * Provides functionality to access information about the dimensions and position of the current frame on the game
 * display
 */
public interface FrameAPI {

    /**
     * Return the width of the frame in pixels
     * @return the width of the frame measured in pixels
     */
    public int getWidth();

    /**
     * Return the x-coordinate of the center of the frame inside the application window
     * @return the x-coordinate of the center of the frame inside the application window
     */
    public int getX();

    /**
     * Return the y-coordinate of the center of the frame inside the application window
     * @return the y-coordinate of the center of the frame inside the application window
     */
    public int getY();

}
