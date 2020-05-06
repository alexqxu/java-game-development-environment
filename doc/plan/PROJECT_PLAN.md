# Implementation Plan 
 
 ### Duballers (14): Abebe Amare(ama100), Ryan Weeratunga (rkw14), Achilles Dabrowski (ajd66), Franklin Boampong(fsb10), Alex Xu (aqx), Vineet Alaparthi (va68)

## API Allocation

- Abebe:
    - Primary: ExternalData, DataLoader, Gravity
    - Secondary: DataParser, GameGenerator, Bots/AI
- Achilles:
	- Primary: Engine API, ObjectFactory, Entity
	- Secondary: DataLoader, ExternalEngine, Gravity

- Alex:
	- Primary: ExternalData, DataLoader
	- Secondary: DataParser, ObjectFactory
	
- Franklin: 
	- Primary: PlayerInternal, PlayerExternal, ControllerAPI, Effects
	- Secondary: Bots/AI, Frame
	
- Ryan:
	- Primary: PlayerInternal, PlayerExternal, ControllerAPI
	- Secondary: GameGenerator, PlayerControls, Effects
	
- Vineet:
	- Primary: DataParser, GameGenerator, Bots/AI
	- Secondary: ExternalData, ExternalEngine, Entity
	
## Timeline 

### Sprint #1

- Have Super Mario Bros run with minimal obstacles (Abebe, Achilles, Ryan, Franklin)

- Have a few enemy bots running in the game (Vineet, Abebe, Ryan)
- Create at least one level for Super Mario Bros (Ryan, Franklin, Alex)
- Implement at least 2 power ups (Alex, Abebe)
- Implement Number of Lives for player and end game if character dies (Achilles)
- Implement features of physics, such as gravity and traveling inside tubes (Alex, Ryan)
- Create a customizable intro screen by having javafx objects only being added if it is in the properties file (Franklin - Game Launcher, Ryan - Specific Game Launcher)
- Use properties file for GUI labels (Ryan)
- Have score increment when player hits brick or eliminates enemy bot (Franklin, Vineet)
- Make window centered on character (Franklin)
- Implement basic JUnit testing (Everyone)

Abebe: 1, 10, 16, 

Alex: 2, 3, 20

Achilles: 6, 7, 22

Franklin: 4,  11, 13, 16, 19

Ryan: 6, 8, 10, 19, 20, 22

Vineet: 27, 37, 38

### Sprint #2

- Include Implementation for Doodle Jump (Everyone)

- Figure out how to make Doodle Jump be a continuous loop (Vineet, Franklin)
- Have score increment differently for Doodle Jump (when character jumps on platform)
- Add coins for Super Mario Bros but not for Doodle Jump (Alex)
- Include all power ups for Doodle Jump and Super Mario Bros(Abebe)
- Create CSV file for storing player information (Achilles)
- Include animation for shooting (power up for Super Mario Bros and always available for Doodle Jump) (Franklin)
- Allow player to choose icon/avatar in introduction screen (Ryan)
- Allow player to have multiple power-ups at one time (Vineet)
- Include additional level for Super Mario Bros (Ryan, Alex)
- Prevent window from shifting if Mario travels to the left or if Doodle Jump travels down (Franklin)
- Implement FX testing (Franklin, Ryan)

Abebe: 12, 28, 33, 40, 42, 

Alex: 18, 30, 34, 39, 

Achilles: 24, 28, 31, 36

Franklin: 9, 15, 17, 21, 26, 41

Ryan: 12, 14,  18, 22, 30, 34

Vineet: 22, 24, 32, 35, 41, 


### Complete

- Implement Multiplayer/Split Screen Display (Achilles, Ryan)

- Store high scores in csv file (Franklin)
- Allow the player to pick between backgrounds for Doodle Jump (pre-chosen or load from file) (Franklin)
- Implement a “Ghost” feature to depict where other character is in Split Screen (Vineet, Ryan)
- Include Help Button to show what controls are available (unique to game) (Abebe)
- Implement wall jumping (Alex, Achilles, Abebe)
- Finalize CSS styling in the Front End (Abebe, Franklin)

Abebe: 5, 46

Alex: 43, 47

Achilles: 29, 46

Franklin: 23, 44, 

Ryan:  25, 45, 

Vineet:  29, 48

