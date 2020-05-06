### Changed Method

- EntityFactoryAPI
   - getEntity(String entityName) is changed to getEntity(String entityName, ParameterController pc);

- EntityAPI
   - getX() and getY() are replaced by getCoordinate()
   - setCoordinate(int x, int y) are added
   - move(int deltaX, int deltaY) are added
   - changed executeMovementScript()'s parameter to Map<String> instead of List<Runnable> since reflection can be used
   and a movement distance needed to be defined as the value for a method call in the key

- GameGeneratorAPI
   - Changed name of setUpGame to setUpApplication

- InternalPlayerAPI
   - Removed id parameters from methods that have them - not necessary since PlayerAPI was never meant to be used with
   a controller-esque class
   - Removed increaseHealth(), decreaseHealth(), increaseScore(), and decreaseScore() because setHealth() and setScore()
   was being used for the aforementioned method's intended functionality
   - Renamed setHealth() to setLives() and getHealth() to getLives() to enforce intent to access/mutate remaining lives
   rather than health for a given game entity
- ExternalDataApi
   - public List<String> getWinnerList(int n) changed to public Collection<WinnerProfile> getWinnerList(int n);
   - Moved playGame() from ExternalDataAPI to ControllerAPI


### Front End Changes in API

#### ExternalPlayerAPI

- public SimpleStringProperty getKeyInput()
   - returns the SimpleStringProperty that changes when the keyboard is pressed
- public SimpleStringProperty getKeyReleasedInput()
   - returns the SimpleStringProperty that changes when the keyboard is released
- public SimpleStringProperty getButtonInput()
   - returns the SimpleStringProperty that changes when the game is paused or resumed

#### InternalPlayerAPI

- public String getCssTag(String name)
   - Given a string, will check the corresponding properties file and return the correct css id
- public String getName(String name)
   - Given a string, will check the properties file and return the display string in the current language
- public String getMethodName(String name)
   - Given a string, will check the properties file and return the name of the method that is invoked
   once the javafx component is pressed
- public Pane getNode()
   - returns Pane to be added to the scene
- public HBox createSideLabel(String labelText)
   - returns an HBox that has a label next to javafx component
- protected void attachCSS(Node node, String className)
   - attaches CSS to the passed in Node
- public void setGameName(String name)
   - sets the game name for specific object
- public String getGameName()
   - returns a String of the game name
- protected void showPopUpError(String errorMessage)
   - handles error for all the components
- public Component getComponent(String componentType, String componentValues)
   - creates a Component and returns it
- public void handleCamera(Pane rightPane, ImageView playerDisplay, ImageView background)
   - passes in objects to handle how the display should move as the player moves across the level
- public ImageView createPlayerDisplay(Entity player)
   - handles creating the player imageview object and then binds it to the cameraview's instance variables
- public void bindBackgroundImage(ImageView background)
   - binds background to the cameraview object
- public Scene getScene()
   - returns the Scene
- public void start(Stage primaryStage)
   - displays the new window
- public void setUpLevel(String levelPath)
   - sets level and creates LevelInfoParser object given String
- public String createXMLFilePath(String extension)
   - creates a String with the ending extension given
- protected SimpleStringProperty getLanguage()
   - returns the SimpleStringProperty for the language
- protected String getString(String propertiesItemName)
   - returns the String from the properties file
- protected void attachStyle(Scene scene, String styleSheet)
   - attaches CSS to the Scene
-  protected SimpleBooleanProperty getSimpleBooleanProperty()
   - returns the state of the ToggleSwitch (true for on, false for off)
- public void handleSceneChange(String value)
   - changes the scene of the stage
- public void addChangeListener
   - adds Listener to ViewActions object
-  public void propertyChange(PropertyChangeEvent evt)
   - in the event of the property changing, to specific action (used for scene changes)
- public void createLayout(String descriptionFilePath, HBox descriptionSceneRoot)
   - creates a layout component and attaches children nodes as specified in the xml
- protected Scene buildScene(String descriptionFilePath)
   - creates scene based on the xml file path given
- public void setComponentFactory(ComponentFactory componentFactory)
   - set ComponentFactory to the one that was passed in
- public void setDisplayInfoParser(DisplayInfoParser displayParser)
   - set the DisplayInfoParser object to the one that is passed in
-  public void setXMLReader(XMLKeyValuePairReader xmlReader)
   - set the XMLKeyValueReader to the one that was passed in
- protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message)
  - shows an alert in the event of an exception during runtime
- protected HBox createHeaderComponents()
   - creates Toggle and language component that is shown on each screen
- protected SimpleBooleanProperty getToggleState()
   - returns a SimpleBooleanProperty that shows the state of the ToggleSwitch
- public Component getChildrenNodes
   - gets Children node for specific layout

### BackEnd API Changes

#### GhostReader
-public String readVirtualKeyPress(double time)
  -Reads virtual key press from Resource bundle.
-public String getLastAction()
- Returns last action executed by Ghost

#### GhostWriter
-public void addEntry(double time, String value)
  -Adds entry to hashmap in bot with time as key and action as value
-public void getEntry(double time, String key)
  -Gets entry from hashmap with time as key
-public void storeMoves(String file)
  -Stores/Writes key-value pairs in hashmap to properties file

####InputStateMachine
-public void pressKey(String key)
  -Adds key to keylist
-public void releaseKey(String key)
  -Removes key from keylist
-public void releaseAllKeys()
  -Removes all keys from keylist
-public List<String> getActiveKeyList()
  Returns current keylist

#### Key
-public String getValue()
  -Returns string of key
-public boolean equals(Key compareKey)
  -Checks if the strings of two keys are equal

####KeyList
-public boolean contains(String keyString)
  -Checks if key is in keylist
-public void addKey(String keyString)
  -Adds key to keylist
-public void removeKey(String keyString)
  -Removes key from keylist
-public List<String> toStringList()
  -Converts keylist to a list of strings with the key values
-private int indexOf(String keyString)
  -Returns index of key in parameter in keylist

####DataController
-public void setControllers(SimpleStringProperty buttonListeners, SimpleStringProperty keyPressed, SimpleStringProperty keyReleased)
  -Instantiates instance variables
-public void setGameName(String gameName)
  -Creates new instance of TopScorersData with game name passed in as argument
-public void setPlayerName(String name)
  -Sets player name
-public Collection<Entity> getViewEntities()
  -Returns all view entities for frontend use
-public void playGame()
  -Instantiates engine and gamecontroller, and starts game loop by calling gamcontroller.playgame()
-public void playGameWithBot(String filepath)
  -Perfoms similar action to playgame method above but with uses different game controller constructor to indicate the game is being played with PlayerAI bot

####EntityBinder
-public Entity getMyViewEntity()
  -Returns view entity
-public Entity getMyBackEndEntity()
  -Returns backend entity

####EntityController
-public Collection<Entity> getAllBackEndEntities()
  -Adds all backend entities from entity binder and returns list of them
-public Collection<Entity> getAllViewEntities()
  -Adds all view entities from entity binder and returns list of them

####InteractionBinder
-public void addBinding(String interactionType, String method)
  -Adds interaction to interaction map if not already present
-public List<String> getMethods(String interactionType)
  -Returns methods associated with interaction passed in as strings
####ParameterController
-public void setImage(String imageDir)
  -Sets image file path
-public void setInteractions(String objectType, String interactionType, String methodCall)
  -Adds new interaction to interaction map if absent
-public void setControllers(String key, String method)
  -Adds control to controller hashmap
-public void setCoordinate(int x, int y)
-public void setHeight(int value)
-public void setWidth(int value)
-public void setType(String value)
-public void setName(String name)
-public Map<String, InteractionBinder> getInteractionMap()
  -Returns interaction map
-public List<String> getInteractionMethod(String entityName, String collisionType)
  -Gets method call for collision type provided on entity provided in argument
-public Map<String, String> getControllerMethod()
  -Returns controller hashmap
-public Coordinate getCoordinate()
-public String getImage()
-public String getName()
-public String getType()
-public PreDefinedMotionHandler getPreDefinedMotionHandler()
-public void addPredefinedMotion(String name, int amount)
-public int getWidth()
-public int getHeight()

####TopScorersData
-public Collection<WinnerProfile> getWinners(int n)
  -Returns number of top scores specified in argument from the arraylist of winners
-public void updateScore(String name, int score)
  -Updates score of player provided as argument as a string

####WinnerProfile
-public int getScore()
  -Returns score of profile
-public String getName()
  -Returns name of profile


####AccelerationCalculator
-public double getAX(double initialXAcceleration, Double fx)
  -Calculates and returns x acceleration
-public double getAY(double initialYAcceleration, Double fy)
  -Calculates and returns y acceleration



####PhysicsCalculator
-public void update()
  -Iterates over all entities and updates their acceleration, velocity, and coordinates using various calculators

####PositionCalculator
-public int getX(int previousX, double velocityX, double xAcceleration)
  -Calculates new x position using velocity, acceleration, and previous x position
-public int getY(int previousY, double velocityY, double yAcceleration)
  -Calculates new y position using velocity, acceleration, and previous y position

####VelocityCalculator
-public double getXVelocity(double aX, double initialXVelocity)
  -Calculates new x velocity using acceleration
-public double getYVelocity(double initialYVelocity, double aY)
  -Calculates new y velocity using acceleration

####AbstractSubApplicator
-public void applyForce(double xForce, double yForce)
  -Applies force to entity and sets this force value in entity as instance variable