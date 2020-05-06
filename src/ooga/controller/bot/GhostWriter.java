package ooga.controller.bot;

import ooga.utilities.exception.WinnerListFileNotFoundException;
import ooga.utilities.exception.WrongFileException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Class that records movements of the player to make a ghost. Refactored from the Game Controller
 * @author Vineet Alaparthi, Abebe Amare, Alex Xu
 */
public class GhostWriter {
    private Map<Double, String> bot;
    private static final String FILE_PATH="resources.modelProperties.Ghost";
    private static final ResourceBundle TEXT = java.util.ResourceBundle.getBundle(FILE_PATH);
    private String hardCodedFilePath = "src/ooga/model/resources/storedmoves.properties";

    /**
     * Constructor for the Ghost
     */
    public GhostWriter(){
        bot = new HashMap<>();
    }

    /**
     * Method for adding an entry to the ghost
     * @param time
     * @param value
     */
    public void addEntry(double time, String value){
        if (!value.equals(TEXT.getString("Null"))){
            bot.put(time, TEXT.getString("Virtual") + value);
        }
    }

    public void getEntry(double time, String key){
        String action = TEXT.getString(String.valueOf(time));

        }

    /**
     * Method for storing the movement history to a file.
     */
    public void storeMoves(String file){
        try (OutputStream output = new FileOutputStream(hardCodedFilePath)) {

            Properties prop = new Properties();

            //Write to properties file
            for (Double time : bot.keySet()){
                prop.setProperty(String.valueOf(time),bot.get(time));
            }

            // save properties to project root folder
            prop.store(output, null);


        } catch (IOException io) {
            //TODO: Catch Exception here.
            throw new WrongFileException(TEXT.getString("ErrorText"));
        }
    }



}
