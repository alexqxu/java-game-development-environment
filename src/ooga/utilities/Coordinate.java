package ooga.utilities;

public class Coordinate {

    private int x;

    private int y;

    /**
     * This class encapsulates the x, and y coordinates of an entity.
     * Can be extended to have three dimensional coordinate system
     * @param x the x location of an entity
     * @param y the y location of an entity
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return the x location of an entity
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return the y location of an entity
     */
    public int getY() {
        return y;
    }

}
