package ooga.controller;
import api.ControllerAPI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Duration;
import ooga.controller.bot.GhostWriter;
import ooga.controller.bot.GhostReader;
import ooga.controller.keyboardInputHandler.InputStateMachine;
import ooga.engine.Engine;
import ooga.utilities.exception.KeyEventHandlingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements ControllerAPI {
    private static final int FRAMES_PER_SECOND = 5;
    private static final int MILLISECOND_DELAY = 200 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final String FILE_PATH="resources.modelProperties.EventHandler";
    private static final ResourceBundle EVENT_TEXT = java.util.ResourceBundle.getBundle(FILE_PATH);
    private static final String KEY_ERROR="KeyError";
    private static final String SET_TEXT="null_method";
    private String filePathBot;
    private Timeline animation;
    private SimpleStringProperty buttons;
    private SimpleStringProperty keyListener;
    //private SimpleStringProperty releaseListener;
    private Engine myEngine;
    private boolean isPaused=false;
    Map<String, Runnable> buttonsController;
    private GhostWriter botWriter;
    private GhostReader botReader;
    private double timer;
    private InputStateMachine multiKeyInputs;
    private String myFolderPath;



    /**
     * it creates entity controller that that has all the entities the game needs and controls their behaviour
     */
    public GameController(SimpleStringProperty buttonListeners, SimpleStringProperty keyListener, SimpleStringProperty releaseListener, Engine engine) {
        buttons = buttonListeners;
        this.keyListener=keyListener;
        myEngine = engine;
        buttonsController=new HashMap<>();
        multiKeyInputs=new InputStateMachine();
        botWriter = new GhostWriter();
        filePathBot = "null";
        botReader = new GhostReader(filePathBot);
    }

    /**
     * Overloaded constructor that takes in the BOT path.
     * @param buttonListeners
     * @param keyListener
     * @param engine
     * @param filePath
     */
    public GameController(SimpleStringProperty buttonListeners, SimpleStringProperty keyListener, Engine engine, SimpleStringProperty releaseListener, String filePath) {
        buttons = buttonListeners;
        this.keyListener=keyListener;
        myEngine = engine;
        buttonsController=new HashMap<>();
        multiKeyInputs=new InputStateMachine();
        botWriter = new GhostWriter();
        botReader = new GhostReader(filePath);
        filePathBot = filePath;
        //this.releaseListener = releaseListener;
    }



    public void playGame() {
        addButtonListeners();
        addKeyListener();
        //addReleaseListener();
        timer=0;
        keyListener.setValue(SET_TEXT);
        //releaseListener.setValue(SET_TEXT);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

    }

    private void step(double secondDelay) {
       timer+=secondDelay;
       multiKeyInputs.pressKey(keyListener.getValue());
       //System.out.println(keyListener.getValue());
       botWriter.addEntry(timer, keyListener.getValue()); //triggered when save is pressed
        if (!filePathBot.equals("null")){
            if (!botReader.readVirtualKeyPress(timer).equals("null")){
                multiKeyInputs.pressKey(botReader.readVirtualKeyPress(timer));
            }
            else{
                multiKeyInputs.releaseKey(botReader.getLastAction());
            }
        }

        //TODO: Release handler implemented
        myEngine.update(multiKeyInputs.getActiveKeyList());
        multiKeyInputs.releaseAllKeys();
        keyListener.setValue(SET_TEXT);
        //releaseListener.setValue(SET_TEXT);
    }

    private void addButtonListeners() {
        buttons.addListener((observable, oldValue, newValue) -> {
                try {
                    String name=newValue.toLowerCase();
                    Method methodToCall=this.getClass().getDeclaredMethod(name);
                    methodToCall.invoke(this);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new KeyEventHandlingException(EVENT_TEXT.getString(KEY_ERROR)+newValue);
                }

        });
    }

    private void addKeyListener(){
        keyListener.addListener((observable, oldValue, newValue) -> {
            if(!isPaused){
                keyListener.setValue(newValue);
                //multiKeyInputs.pressKey(keyListener.getValue());
            }
        });
    }

    public void applyEffects() {

    }

    public void applyPhysics() {

    }

    private void pause(){
        isPaused=true;
        animation.pause();
        botWriter.storeMoves(filePathBot);
    }

    private void resume(){
        isPaused=false;
        animation.play();
    }

    private void restart(){

    }
    private void save(){

    }
}
