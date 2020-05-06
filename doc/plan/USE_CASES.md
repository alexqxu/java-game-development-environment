# Use cases - Duballers (Team 14)

The following are all the 48 of the use cases brainstormed for the final project. These use cases
consider the game engine implementation, the data reading as well as the player use cases involved
in the game development and game use.

1. Moving Mario - Mario will be able to move based on user input, including left, 
right, and jumping up. Pressing the left key will indicate moving to the left, 
pressing the right key moves Mario to the right, and pressing the up key makes 
Mario jump. Mario will only be able to jump when he is on a platform or if he is
on the ground, so he is unable to double jump. If Mario comes in contact with an
unblocked tunnel, the player will be able to travel up or down the pipe, depending
on the direction. If Mario falls in a hole or runs into an obstacle/enemy, player 
will lose a life and continue where the avatar left off. As Mario travels to the
right, the window will be centered around the avatar. If Mario travels to the left,
the window will show the same section of the level and will not allow Mario to 
pass the left boundary. If Mario travels to the left,
the window will show the same section of the level and will not allow Mario to 
pass the left boundary. As Mario travels to the
right, the window will be centered around the avatar. 

2. Score Handling with Super Mario - When avatar destroys brick or enemy, the score 
will increase. Additionally, if the player collects coins, the score will similarly
increase. When a player completes the level, the extra time remaining will be 
converted into a value and added to the score. When the player loses all of their 
lives or completes all the levels, will have the ability to store the name, score, 
and image in the CSV file. This CSV file will be used to show the high scores in
the main menu. 

3. Lives and Game OVer in SM - If Mario falls in a hole or runs into an obstacle/enemy, the player 
will lose a life and continue where the avatar left off. When the player loses a 
life when the remaining life count is already zero, the stage will disappear from
the player's view and a 'Game Over' window will display. If the player selects 
the option to continue the game displayed as 'Yes' in the window, Mario will spawn in the beginning
of the stage that was just being played with the default amount of lives. The player's
score will also be reset to zero upon continuing the game. If the player clicks
the option to exit the game displayed as 'No' on the Game Over, they will be brought
back to the game selection window.

4. Pausing and Continuing Super Mario game - When the user presses a pause button, the game will be paused
and the current arrangements of the objects in the screen will be paused and no item will be moving. When the player
wants to continue the game, it can press the continue button and play the game with the paused arrangements of the
objects on the screen.

5. Saving the current configuration and playing the game later - If the user wants to exit the game, but wants to play
the game using the current arrangements of the objects in the screen, it can save the current configuration and play
 it later by loading the saved file.

6. Mario traveling in a tunnel - If Mario comes in contact with an unblocked tunnel, the player will be able to travel up or down the pipe, depending on the direction. 

7. Mario falling and losing a life - If Mario falls in a hole or runs into an obstacle/enemy, the player will lose a life and continue where the avatar left off. 

8. Window Appearance as Mario moves - As Mario travels to the right, the window will be centered around the avatar. If Mario travels to the left, the window will show the same section of the level and will not allow Mario to pass the left boundary. If Mario travels up, the window will not shift up unless because of a tunnel. If Mario falls and there is nothing underneath, the window will remain the same. 

9. Letting Mario use a moving bridge/bar to cross open space - As Mario travels to the right  and it encounters an open space that cannot be jumped directly, there will be moving bars that Mario can stand on and move to the direction that the bar travels to.

10. Mario obtains Flower up - When mario opens/hits a mystery box, a flower icon will appear on top of the block. When Mario comes in contact with the flower icon, the image of the avatar will change. Additionally, when the spacebar is pressed, a ball will be emitted in the direction that Mario is facing. When an enemy comes in contact with the ball, then the enemy will lose a life or will be destroyed if its life is one. 

11. Selecting Game in Game Launcher - When the image of the a game is selected, then the specific game introduction scene will open. This page will show high scores and play buttons, rules, and commands. Once the play button is selected, the user will be brought to the actual gameplay scene. If the high score button is selected, then the table showing the high scores for the specific game will appear. 

12. Mario uses a vertical pole to jump or climb - If Mario is traveling to the right and reaches an open space that has higher height than Mario can jump, it can use a vertical pole to go down or go up.

13. If the initial configuration document to set up a particular game selected is not present, a custom error telling the user which file he or she is missing will be displayed as the string to the error message. 

14. Mario reaches to the castle and the flag will show up at the top of the roof - When Mario reaches to the castle, it can get into the castle and a flag will show up at the top of the roof after a while.

15. When the user pauses a game and returns to the home page, all the most recent information about the user, i,.e. the users’ recent score, the users’ current configurations will be stored in a csv file to serve as user default specifications.

16. Mario comes in contact with an enemy - If Mario jumps and lands on an enemy, the enemy will die. If Mario touches the enemy anytime else, then Mario will lose a life. 

17. Selecting Avatar - In the specific game introduction screen, there will be a combo box that lists all the images that are in a specific resources directory. Once one is selected, that will be the imageview object that the player will control and move. If the user picks the option “other” from the combo box, a file chooser will open and the user can load an image.

18. Mario kills enemies using weapons - Mario will shoot fires to its enemies which are on its way. The enemies will die or survive depending on the life they have and they can kill Mario if it does not have enough life left.

19. Player loads own avatar - When an image is loaded from the file chooser, then the image will be retrieved and resized so that it is the same size as all the other avatar images. Then this will be assigned to the imageview object in the front end

20. Handling the End of Level - When Mario reaches and touches the flagpole, then this will indicate that the player has gotten to the end of the level. Depending on high on the pole, then the score will be incremented accordingly, where the higher Mario is, the more points he receives. 

21. Handling the user Login - Once the user begins the game the user will be required to input their basic details so we could map the user to the score and that way be able to display the high scores page with names. Also, this way every column in the csv will be a new user and the rows for that column will be the user’s info for the game played.

22. Mario comes in contact with power up objects -  If  Mario comes to contact with power up objects like coins, power icons and foods, the power up objects will disappear and Mario gets more life.

23. Loading High Scores for Specific Game Type - When the user selects the High Score button in the specific game introduction screen, then the csv file for the specific game will be uploaded and sorted based on the scores from highest to low. Then the top 5 scores will be displayed along with the player’s name.

24. Enemies use weapons to kill Mario - As the player character moves to higher levels, the enemies will have weapons which they can use to shoot Matio. Whenever Mario is shot by the enemies’ weapons, it will lose life.

25. Displaying Average rating - In the Game Launcher screen, the ratings of each game will be retrieved. Then for each game, the average rating will be displayed underneath the Image of the game and next to the description of the game.

26. Exiting Game without saving - When the user is in the game, selects pause game, and hits the exit button, then the user will return to the Game Launcher screen. The score will not be stored in the high score csv file. 

27. Selecting a game with an incomplete configuration file - If the user selects an image that has an incomplete configuration file, then a new scene will appear in the window and it will display the message “Game is still under development.” There will also be a back button that returns the user to the Game Launcher screen. 

28. Enemies follow Mario - As Mario progresses to higher levels, some enemies will follow Mario.  These enemies will compete to collect the power ups with Mario and are able to attack and kill Mario.

29. Multiplayer in Super Mario Bros - When multiplayer is selected in the specific game introduction screen, then a window will open for each player. As the players go through the level, a “ghost” image will show where the other player is. If the other player is too far ahead of the current player, then the ghost will not appear in the window. This will also occur if the current player is too far ahead of the other player. At the end of the level, the player with the larger time remaining will be the winner.

30. Doodle Jump comes in contact with Spaceship powerup - When the character touches the spaceship icon, the character is able to travel for a period of time without being affected by enemies or having to jump. The score will continue to be incremented as the character travels in the spaceship. 

31. Doodle jump screen being toroidal - In developing the doodle jump game, the condition where the doodle is moved to the far end of one screen such that it moves out of the screen boundary, the game would cause the doodle object to reappear from the other end of screen.
 
32. The direction to which the Doodle moves is controlled by keys  - The user controls the direction to which the Doodle is moving using keys that are specified by the user in the configuration file. The Doodle can move left, right, and top by a certain pixel.

33. Doodle Jump hits enemies with shooting ability - When the user presses the spacebar, then the player will emit a shot directly above it. If it hits an enemy, then the enemy will lose a life. If the enemy’s life becomes 0, then the enemy will disappear. The shot goes through the platforms and the platforms are unaffected. 

34. Doodle Jump touches enemy - When the player jumps on an enemy, the player will automatically jump up and the enemy will automatically disappear, regardless of their number of lives. 

35. Doodle can jump higher when it lands on bars with spring - When Doodle lands on bars which have springs on them, Doodle can jump up by a higher value. The value will be higher than the number of pixels that Doodle can jump.

36. Doodle Jump falls off Window - When the character travels downward and escapes the view of the window, then the player will lose a life and start where he left off. If the player has no more lives, then the score will be recorded and the game over screen will appear. 

37. A game designer wishes to change the image used for one of the non-player characters. What the game designer would need to do is go into the game configuration files (XML), locate the location of where the image URL/filepath of the image used for this NPC is located, and then change this to the new image file they want to use as the image.

38. Reading powerup from configuration file - Each powerup will be defined as a ScrollableObject and it will define how enemies and how the player will react when this powerup comes in contact with it. These methods will be mapped in the properties file and will be in reflection to create these objects. 

39. Users can rotate Doodle using keys - Doodle can rotate clockwise and counterclockwise direction based on keys pressed by the player. The keys will be specified in the configuration file.

40. Doodle breaks breakable scroll objects - When Doodle jumps on breakable bars, the bars will be broken and Doodle will get more lives. The broken bar will disappear.

41. Some Scroll objects move left/right - Some Scroll bars  move left and right automatically. Thus, the Doodle can jump on them and get more lives or lose lives.

42. Doodle jump travels downward - As described earlier, when the character moves up, then the window will be centered around the character. If the character moves down, the window will remain the same and will not move with the character. 

43. Doodle lands on a bar which has a flipper and it rotates twice - When Doodle lands on a bar that has flipper scroll object, the Doodle will jump to higher bars and rotate twice while it jumps to the higher bars. 

44. Loading Background Image in Game - Each configuration file will store the path of the image that will be shown when the user is playing the game. When the game is selected in the game launcher page and the user presses the play button in the specific game intro screen, then the xml file will be loaded and the background image will be retrieved. 

45. A game designer wants to incorporate a new type of object in the game (like a new power up). What the designer would need to do is go into the configuration file, find the correct place where all of the game objects are specified, and specify the new power up game object. For this new power up game object, the image file would need to be specified for the appearance. In addition, the number of sides, and which sides are solid/hollow need to be specified. Furthermore, the movement pattern and collision behavior needs to be specified as well, depending how the game designer wants to implement the power up.

46. A game designer wants to change the texts on the UI components and make those components custom to their game version and game genre, the user could just go into the properties file and set the UI component texts and specification according to their desired game.

47. A game designer wants to implement a moving Frame, but have the frame move to a certain location on a certain event. This can be specified in the XML configuration file. Sample event include when the points reach a certain number, when the number of lives reaches a certain number, when the player reaches a certain location, and when the timer reaches a certain time.

48. Enemies can follow Doodle - Enemies can follow the Doodle and compete for power ups and they can attack and kill it.


