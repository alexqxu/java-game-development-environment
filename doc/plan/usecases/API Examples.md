# API Examples
## The Duballers (Team 14): Abebe Amare(ama100), Ryan Weeratunga (rkw14), Achilles Dabrowski (ajd66), Franklin Boampong(fsb10), Alex Xu (aqx), Vineet Alaparthi (va68)

### Use Cases
```java

/**
 * This Class describes a basic implementation of the 
 * use cases. This class shows an example implementation
 * for how the various APIs would be used to handle
 * the use cases specified.
 **/

public Class UseCaseExamples implements DataAPI, ExternalEngineAPI, BotAPI
{

/** This use case 23
 From the ExternalDataAPI, using getWinnerList and getHighScores
 **/
private void displayHighScores(){
    //reflection will eventually be used to create this
    TableView table = new TableView();
    table.setEditable(false);
    TableColumn names = new TableColumn("Name"); //will come from properties file
    TableColumn scores = new TableColumn("Scores");//will come from properties file
    
    names.setCellValueProperty(new PropertyValue(getWinnerList()));
    scores.setCellValueProperty(new PropertyValue(getHighScores()));
    
}

/**This use case encompasses use case 3
* This sets the Engine API boolean for isGameFinished to ensure that the game ends 
**/
private void endGame(){
    if (!inBounds()){
        isGameFinished = true;  
        quitGame();
    }
}

// This method ends (quits) the application of the game

private void quitGame(){
    System.exit(0);
}

/**
 * Use case 7
 * This use case is a simple implementation 
 * of a player going out of bounds and losing a 
 * life
 * 
 * Uses FrameAPI getWidth
 * 
 **/

public boolean isOffScreen(){
    return player.getY() >= getWidth();
}


/** Use case 16
 *   Using InternalAPI method decreaseHealth
 **/
private void collisionWithEnemy(){
    if (checkCollision())//determines if collision will destroy enemy or will decrement life of player
        decreaseHealth();
}

/**
 * Use case 29
 * 
 **/
private void showGhost(){

    //list of runnables that describe movement
    // load a csv file with movement methods calls for the bot to execute so it may play against the human player
    BotAPI bot = new GameBot(); //Assume GameBot class implements BotAPI
    List<Runnable> botInputs = new currentMirror.mkToolBox.compile( new CSVReader(new File("C:\Users\Alex\Downloads\lastInputs.csv")).nextLine() ); 
  
  //Execute each method call in sequence with a loop
    for(Runnable action : botInputs){
        bot.executeNextAction(action);
    }
    
    
    
}


```