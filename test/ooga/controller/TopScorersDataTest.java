package ooga.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopScorersDataTest {

    private TopScorersData topScorersData=new TopScorersData("FIFAGame");

    @Test
    void getWinners() {
        int[] scores={49,39,26,25,19};
        String[] names={"Abebe","Terry","Kaka","Messi","Ronaldo"};
        int count=0;
        for(WinnerProfile winnerProfile:topScorersData.getWinners(3)){
            assertEquals(names[count],winnerProfile.getName());
            assertEquals(scores[count],winnerProfile.getScore());
            count++;
        }
    }

    @Test
    void updateScore() {
        topScorersData.updateScore("Messi", 25);
        topScorersData.updateScore("Kaka", 26);
        topScorersData.updateScore("Terry", 39);
        topScorersData.updateScore("Ronaldo", 19);
        topScorersData.updateScore("Abebe", 49);
        assertEquals(3, topScorersData.getWinners(3).size());
    }
}