package ooga.parser.frontFacingParsers;

import ooga.controller.WinnerProfile;
import ooga.utilities.exception.ParserException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to parse the high scores. It returns a List of Winner Profiles.
 * @author Alex Xu
 */
public class TopScoreParser {
    public static final String TOP_SCORE_SUFFIX = "highScores/topScores.csv";

    private List<WinnerProfile> myWinners;
    private String myFilepath;

    /**
     * Constructor for the TopScoreParser. Takes in a folderpath for the game to load high scores from.
     * @param folderPath
     */
    public TopScoreParser(String folderPath){
        myWinners = new ArrayList<>();
        myFilepath = folderPath + TOP_SCORE_SUFFIX;
        readCSVData();
    }

    /**
     * Returns the Profiles of the players that are read in.
     * @return List of encapsulated WinnerProfile objects.
     */
    public List<WinnerProfile> getProfiles(){
        return myWinners;
    }

    private void readCSVData(){
        String row = "";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(myFilepath));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(":"); //TODO: Load the delimiter from a properties file as well.
                addWinnerProfile(data);
            }
            csvReader.close();
        }
        catch(IOException e){
            throw new ParserException(e.getMessage());
        }
    }

    private void addWinnerProfile(String[] data){
        String name = data[0].trim();               //TODO: Load these values/indices in through a properties file
        String score = data[1].trim();
        WinnerProfile winnerProfile = createWinnerProfile(name, score);
        myWinners.add(winnerProfile);
    }

    private WinnerProfile createWinnerProfile(String name, String score){
        int numericScore = Integer.parseInt(score);
        return new WinnerProfile(name, numericScore);
    }
}
