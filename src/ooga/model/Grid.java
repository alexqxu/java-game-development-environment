package ooga.model;

import ooga.parser.components.GeneralInfoParser;

/**
 * This is a hard-coded Grid that has since been deprecated.
 */
@Deprecated
public class Grid {
    private GeneralInfoParser generalInfoParser;
    private Entity[][] grid;
    private int rowSize;
    private int colSize;
    private int screenSize = 50;
    private int screenWidth = 50;
    public Grid(GeneralInfoParser generalInfoParser){
        this.generalInfoParser=generalInfoParser;
        initializeGrid();
    }

    private void initializeGrid(){
        rowSize=100; // calculate it later
        colSize=100;
        grid=new Entity[rowSize][colSize];
    }

    /**
     * The grid will store the entity at the row and col specified by the coordinate of the Entity
     * @param entity the entity to be added and mounted to the screen
     */
    public void mountEntities(Entity entity){
        int row=entity.getParameterController().getCoordinate().getX();
        int col=entity.getParameterController().getCoordinate().getY();
        entity.setCoordinate(100+100*row, 100+100*col);
        if(inBounds(row, col)){
            this.grid[row][col]=entity;
        }
    }
    private boolean inBounds(int row, int col){
        return (screenSize>=row && screenWidth>=col);
    }

    public Entity getGrid(int row, int col){
        return grid[row][col];
    }


    

}
