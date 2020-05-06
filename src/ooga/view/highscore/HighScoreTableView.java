package ooga.view.highscore;

public class HighScoreTableView {
    private String myGameName;
    public HighScoreTableView(String gameName){
        myGameName = gameName;
    }
    private class Player{
        private String myName;
        private String myScore;
        public Player(String name){
            myName = name;
            //TODO get score from csv file
            myScore = "30";
        }
        public String getName(){ return myName;}
        public String getScore(){ return myScore;}
        public void updateHighScore(){
            // TODO read from csv file once again
        }
    }
}
