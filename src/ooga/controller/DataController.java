package ooga.controller;

import api.ExternalDataAPI;
import javafx.beans.property.SimpleStringProperty;
import ooga.engine.Engine;
import ooga.model.Entity;

import java.util.Collection;
import java.util.List;

public class DataController implements ExternalDataAPI {

    private EntityController entityController;
    private GameController gameController;
    private SimpleStringProperty buttonListeners;
    private SimpleStringProperty keyPressed;
    private SimpleStringProperty keyReleased;
    private Engine myEngine;
    private TopScorersData topScorersData;
    private String playerName;
    private String myFolderPath;
    private int myLevel;

    public DataController(int level, String folderPath){
        entityController = new EntityController(level, folderPath);
        myFolderPath = folderPath;
        myLevel = level;
//        topScorersData=new TopScorersData("Mario");// get the name from view later

    }

    public void setControllers(SimpleStringProperty buttonListeners, SimpleStringProperty keyPressed, SimpleStringProperty keyReleased){
        this.buttonListeners=buttonListeners;
        this.keyPressed=keyPressed;
        this.keyReleased = keyReleased;

    }
    @Override
    public String getGameName() {
        return null;
    }

    @Override
    public int getGameLevel() {
        return 0;
    }

    @Override
    public List<Integer> getHighScores(int n) {
        return null;
    }

    @Override
    public Collection<WinnerProfile> getWinnerList(int n) {
        return topScorersData.getWinners(n);
    }

    @Override
    public List<String> getOrderOfGame() {
        return null;
    }

    @Override
    public void loadGameData() {

    }

    @Override
    public String getGameConfiguration() {
        return null;
    }

    @Override
    public EntityController getEntityController() {
        return entityController;
    }

    public void setGameName(String gameName){
        topScorersData=new TopScorersData(gameName);
    }

    public void setPlayerName(String name){
        playerName=name;
    }
    /**
     * Method to provide entities to front end
     * @return all the entities from controller
     */
    public Collection<Entity> getViewEntities(){
        return entityController.getAllViewEntities();
    }

    public void playGame(){
        myEngine = new Engine((List<Entity>) entityController.getAllBackEndEntities(), myFolderPath, myLevel);
        gameController=new GameController(buttonListeners, keyPressed, keyReleased, myEngine);
        gameController.playGame();
    }

    public void playGameWithBot(String filepath){
        myEngine = new Engine((List<Entity>) entityController.getAllBackEndEntities(), myFolderPath, myLevel);
        gameController=new GameController(buttonListeners, keyPressed, myEngine, keyReleased, filepath);
        gameController.playGame();
    }
}
