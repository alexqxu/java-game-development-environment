package ooga.parser.utilities;

/**
 * The purpose of this class is to provide an interpretation/conversion of the Grid Coordinates into actual X and Y
 * coordinates, with respect to the level bounds. This is a utility class.
 * @author Alex Xu
 */
public final class GridLocationInterpreter {
    public static final int GRID_SIZE = 30; //TODO: Pass this in through a properties file

    private GridLocationInterpreter(){
        throw new AssertionError();
    }

    public static int projectGridToPixel(int input){
        return input*GRID_SIZE;
    }
}
