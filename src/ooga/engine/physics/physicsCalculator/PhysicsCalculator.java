package ooga.engine.physics.physicsCalculator;

import javafx.scene.image.Image;
import ooga.engine.boundaryHandler.BoundaryHandler;
import ooga.model.Entity;
import ooga.parser.components.LevelInfoParser;
import ooga.utilities.exception.EntityBoundsException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;

/**
 * This class calculates acceleration, velocity, and position for an entity based on the force exerted on the entity
 * object
 */
public class PhysicsCalculator {

    private List<Entity> entityList;
    private AccelerationCalculator accelerationCalculator;
    private VelocityCalculator velocityCalculator;
    private PositionCalculator positionCalculator;
    private int myVerticalBoundary;
    private static final String FORCE_TYPE_HIT="Hit";
    private static final String FORCE_TYPE_CONSTANT="Constant";
    private static final String LEVEL_PATH_PREFIX = "level_";
    private static final String LEVEL_PATH_SUFFIX = ".xml";
    private static final int BOUNDARY_PADDING = 75;


    /**
     *
     * @param entityList the list of all the backEnd entities
     * @param gameFolderPath the path to the current game level XML file
     */
    public PhysicsCalculator(List<Entity> entityList, String gameFolderPath, int levelNumber) {
        this.entityList=entityList;
        positionCalculator=new PositionCalculator();
        accelerationCalculator=new AccelerationCalculator();
        velocityCalculator=new VelocityCalculator();
        LevelInfoParser lvlInfoParser = new LevelInfoParser(gameFolderPath + LEVEL_PATH_PREFIX + levelNumber + LEVEL_PATH_SUFFIX);
        Image backgroundImage;
        try {
            backgroundImage = new Image(new FileInputStream(lvlInfoParser.getBackgroundImage()));
        } catch (FileNotFoundException e) {
            throw new EntityBoundsException(e.getMessage());
        }
        myVerticalBoundary = (int) backgroundImage.getHeight() - BOUNDARY_PADDING;
    }

    /**
     * it calculates the final position of the entities based on the force exerted on them
     */
    public void update(){
        for(Entity entity:entityList){

            double accelerationX=accelerationCalculator.getAX(entity.getForce().getFX(), entity.getAcceleration().getXAcceleration());
            double accelerationY=accelerationCalculator.getAY(entity.getForce().getFY(), entity.getAcceleration().getYAcceleration());
            double xVelocity=velocityCalculator.getXVelocity(accelerationX,entity.getVelocity().getxVelocity());
            double yVelocity=velocityCalculator.getYVelocity(accelerationY, entity.getVelocity().getyVelocity());
            int positionX=positionCalculator.getX(entity.getCoordinate().getX(), xVelocity, accelerationX);
            int positionY=positionCalculator.getY(entity.getCoordinate().getY(), yVelocity, accelerationY);

            positionX = BoundaryHandler.checkX(positionX);
            positionY = BoundaryHandler.checkY(positionY);

            entity.setCoordinate(positionX, positionY);
            entity.setAcceleration(accelerationX, accelerationY);
            entity.setVelocity(xVelocity, yVelocity);

            if(entity.getForce().getForceType().equals(FORCE_TYPE_HIT)){
                entity.setAcceleration(0,entity.getAcceleration().getYAcceleration());
                entity.setVelocity(0,entity.getVelocity().getyVelocity());
                entity.getForce().setForceType(FORCE_TYPE_CONSTANT);

            }
            entity.setForce(0,0);

            if(entity.getCoordinate().getY()>= myVerticalBoundary && entity.getName().contains("Player")){
                entity.setCoordinate(entity.getCoordinate().getX(), myVerticalBoundary);
                entity.setAcceleration(0, 0);
                entity.setVelocity(0,0);
                entity.setForce(0,0);
               // break;
            }
            if(entity.getCoordinate().getX()<=0 && entity.getName().contains("Player")){
                entity.setCoordinate(0, entity.getCoordinate().getY());
                entity.setAcceleration(0, 0);
                entity.setVelocity(0,0);
                entity.setForce(3,0);
                // break;
            }
            if(entity.getCoordinate().getY()<=0 && entity.getName().contains("Player")){
                entity.setCoordinate(entity.getCoordinate().getX(), 0);
                entity.setAcceleration(0, 0);
                entity.setVelocity(0,0);
                entity.setForce(0,3);
                // break;
            }



        }

    }

}
