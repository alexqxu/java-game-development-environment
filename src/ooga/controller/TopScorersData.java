package ooga.controller;


import ooga.utilities.exception.WinnerListFileNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TopScorersData {

    private static final String FILE_PATH="resources.dataProperties.WinnerListText";
    private ResourceBundle textForException= java.util.ResourceBundle.getBundle(FILE_PATH);
    private static final String ERROR_KEY="ErrorMessage";
    private static final String FILE_PATH_FOR_WINNER_LIST="FilePathForWinnerList";
    private static final String FILE_EXTENSION=".txt";
    private String fileName;

    private Map<String, Integer> unSortedPlayersScore;
    private Map<String, Integer> reverseSortedPlayersScore ;

    /**
     * This class handles the top scorers of a game and returns the winners as a collection by reading and writing
     * .txt file
     * @param nameOfGame the name of the game we want to create winner list for
     */
    public TopScorersData(String nameOfGame) {
        unSortedPlayersScore=new HashMap<>();
        reverseSortedPlayersScore = new LinkedHashMap<>();
        fileName=textForException.getString(FILE_PATH_FOR_WINNER_LIST)+nameOfGame+FILE_EXTENSION;
    }

    /**
     * This method returns collections of players with their scores
     * @param n the number of players' score we want to show
     * @return collection of player profiles
     */
    public Collection<WinnerProfile> getWinners(int n){
        List<WinnerProfile> winnerList=new ArrayList<>();
        sortPlayersScoreInReverseOrder();
        updateTopScorersInTheTextFile();
        int counter=0;
        for(String player: reverseSortedPlayersScore.keySet()){
            if(counter<n){
                winnerList.add(new WinnerProfile(player, reverseSortedPlayersScore.get(player)));
                counter++;
            }
        }
        return winnerList;
}

    /**
     *
     * @param name the name of the new player we want to create a profile for
     * @param score the score that the new player scored
     */
    public void updateScore(String name, int score) {
        unSortedPlayersScore.put(name,score);

    }

    /**
     * This method reads previous winners from .txt file and store them in the unSortedPlayersScore map
     */
    private void readTextFileToGetScores(){
        File mappingTextFile = new File((fileName)).getAbsoluteFile();
        try (Scanner scanner = new Scanner(mappingTextFile)) {
            while (scanner.hasNext()) {
                String[] nameWithScore=scanner.next().split(":");
                unSortedPlayersScore.put(nameWithScore[0],Integer.parseInt(nameWithScore[1]));
            }
        } catch (FileNotFoundException e) {
            throw new WinnerListFileNotFoundException(textForException.getString(ERROR_KEY+ fileName));


        }

    }

    /**
     *Sorts the new player's score and the score of the old top scorers in reverse order by their score
     */
    private void sortPlayersScoreInReverseOrder(){

        if(Files.exists(Paths.get(fileName))){ readTextFileToGetScores(); }
        unSortedPlayersScore.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedPlayersScore.put(x.getKey(), x.getValue()));
    }


    private void updateTopScorersInTheTextFile(){
        try {
            FileWriter writer = new FileWriter(fileName, false);
            Set<String> playerNames = reverseSortedPlayersScore.keySet();
            for(String playerName:playerNames){
                writer.write(playerName+":"+reverseSortedPlayersScore.get(playerName));
                writer.write("\r\n");  // write a new line
            }
            writer.close();
        } catch (IOException e) {
            throw new WinnerListFileNotFoundException(textForException.getString(ERROR_KEY));
        }
    }

}



