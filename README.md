Java Game Development Environment
====

This project implements a player for multiple related games.

Abebe Amare, Achilles Dabrowski, Alex Xu, Franklin Boampong, Ryan Weeratunga, Vineet Alaparthi

### Timeline

Start Date: 4/2/20

Finish Date: 4/30/20

Hours Spent: Approximately 700 Hours Collectively

### Primary Roles

Ryan worked exclusively on the frontend, and was responsible for features such as the level editor, reflections that were
 mostly used in overall design implementation of view components, and attaching entities on screen for view. Franklin also 
 worked exclusively on the frontend, and was responsible for features such as the login screen, all css stylings for the 
 project, and the View Scenes. Both Ryan and Franklin collaborated and pair programmed heavily in the design and implementation 
 of the frontend. 

Vineet worked on the backend, and was responsible for features such as the Player AI. Achilles worked on the backend, 
and was responsible for features such as the implementation of the sub-applicator classes. Abebe worked on the backend, 
and was responsible for features such as the enemy bots. Alex worked with both the frontend and backend, and focused on 
the design of the game engine and various parsers as well. The entire backend (Vineet, Achilles, Abebe, and Alex) all 
worked together extensively to not only come up with the high level design of the backend (which was revamped multiple 
times throughout the course of the project) but also to implement the design (this was done through pair-programming, 
which was done extensively).

### Resources Used

* CS 308 Website, TA Ben Xu, Professor Duvall
* Various online sources for Images and Game design inspiration

### Running the Program

Main class: ```Main.java``` located at ```src -> ooga```

Data files needed:
* All files located in the ```data``` folder, specifically the ```Games``` folder
* All files located in the ```resources``` folder under ```src```

Features implemented:

* Play platformer/scrolling games from Data files
* Create new levels with the level editor GUI
* Design custom platformer/scrolling games:
    * Utilize Newtonian mechanics to implement movements
    * Implement interactions between objects on collisions
    * Customize user controls
* Pausing games
* Running multiple games at once
* Top scores listings and user accounts

### Notes/Assumptions

### Assumptions or Simplifications:

#### Frontend
The main assumptions that were made to simplify the design was assuming that only the Game Description View would make use of the xml parsing to create UI components. We assumed that the other screens that required frequent changes such as the main game view should be hard coded using JavaFx techniques. This decision was mainly because a changing screen requires several changes to UI components and this requires a readily dynamic implementation of the front end. Due to reflection while parsing components from XML to screen, it is much difficult to attach changing features such as when an object is moving on a pane to our implementation. This made our code much simpler as we assumed the xml-css implementation would be used for mostly static, non-changing UI scenes.

Also, for the login and signup screens we used manual JavaFx implementations other than required XML-CSS implementation since those screens seem static and are screens that users would prefer to change structural appearance. We evaluated the priority of other game implementations and realized that the team’s priority was to have a good design implementation of Game view rather than an easy to change login or sign up screen. Also, when authenticating information, we required actual data from UI components, but if we had used the xml-css approach we would have needed to figure out how to readily get text entered by the user and this would have taken time so we decided not to use that approach.

#### Backend
Several assumptions were made while designing this project in the backend. First, we assumed that all platformer/scrolling games will have a camera that follows the player in a defined general direction (such as left, right, up, or down). Second, we assumed that these games will have defined objects that shall move around in a 2D game level, with a background and objects that are either stationary or mobile. Originally we had intended there to be a separate class for enemy entities, but this was abstracted to also include stationary objects that do not have a particular “special” behavior. 

All entities also have a predefined “health”. Finally, we assumed that each entity can have a defined interaction with another entity, but in the current implementation of our Game Engine, these are limited to Newtonian forces, increasing the score, and decreasing health of the entity. However these can be expanded upon easily (more below). Finally, we assumed that the goal of the games is to accumulate as many points as possible.

These assumptions helped us narrow our scope for the design of the back-end of our project, but remains flexible which allows for the creation of any type of game that exists within this scope.

### Interesting data files:

Please see ```Games``` folder for sample games.

### Known Bugs:

* Collisions for AI/Ghost controlled character is not working
* Score Display at the End of the Game is not working
* Boundary Types (such as Toroidal) are not yet complete

### Impressions

Being able to create a program that allows others to create platformer/scrolling games was an interesting journey and and
allowed us to demonstrate the software design skills that we have learned through this course. Being able to work through
the problem at hand, and define the problem by setting goals and limitations early on allowed us to focus our efforts and
work toward a balance between making our program flexible to satisfy its purpose, while not too general purpose so much that
it loses focus. 

Implementing core components such as the Game Engine (Physics Engine) and User Accounts (Register/Login verification) were
interesting design challenges for us. It was especially satisfying to see the entire project come together based on the software
architecture design that we had initially come up with in the beginning, despite the changes we had made to various components
and their sub-components.
