# Design Document
### Names
Abebe Amare, Achilles Dabrowski, Alex Xu, Franklin Boampong, Ryan Weeratunga, Vineet Alaparthi

### Roles
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

### Design Goals
The main design goal was to make the frontend code as flexible as possible while maintaining good code design. Hence, we wanted to make it so easy to add any UI component of choice as well as enable the user to change any styling or positions of UI components. This way, the front end is actually specified by the designer.

For the design of the backend, the main design goal was making it as easy as possible to accommodate for the addition of new features, especially to include all of the features that are characteristic to platformer/scrolling games. Namely, any new interactions the user might want between objects on the screen. In order to do this, we decided to construct a physics engine, in which all actions were enacted by applying a force onto the desired object in order to achieve that action. In this manner, in order to add a new feature such as “jump” or “float” all that needs to be done is create a new subApplicator class (which extends AbstractSubApplicator) that states the x and y forces that need to be applied in order to achieve that action. In addition to this design goal, we also wanted to make it easy to add new things to be checked in the main game loop. In order to do this, we created “Triggers” which were created every time an interaction occurs. This trigger then enacted an action based on the type of trigger. Because of this design choice, when we decided to add features like enemy bots and the end of a game, we simply created a new trigger class (which extended TriggerHandler)  which contained a “getTriggers” method. This design choice greatly simplified the addition of many features.

### High-Level Design
#### Frontend
The high-level design implementation for the front end design was to have an XML serve as the structure of the view and the CSS serve as the styling of the view. This was kind of a mini-HTML CSS implementation and we did well to write our own code for parsing the xml file and attaching the css stylings for the different UI components possible. The idea was to allow the user to decide the structure they want, go into an xml file and implement the layout arrangements if the current arrangement does not meet their preference.. Also, the various IDs of the various UI elements are set in the XML file, thus one could just write the CSS styling of preference and see the results play out.

#### Data
The Data component of this project (```parser``` package) mostly consists of Parsers that read in required Game XML files, and provide the needed information to the rest of the program. This consists of smaller Parser components that are responsible for reading in individual files (such as the ```characterConfig.xml``` file, front-facing parsers that combine parser components together to retrieve information and assemble them into Model objects (```ParameterController``` objects). Utilities such as the ```GridInterpreter``` (converts Grid locations to back-end coordinate locations) help in this process. 

Finally, the ```XMLValidator``` class works to validate input XML files against XSD schema files. XSD schema files define the expected structure for an XML file, and what types of data are allowed. This validator class helps to prevent parsing exceptions when a user attempts to load an invalid XML file. It also returns a descriptive message when there is an issue to aid in debugging.

#### Model
Every object in the game (the Model) is an instance of the “Entity” class. Each entity contains an instance of the “ParameterController” class which contains all of the information provided about the entity in the XML (the various parsers create the entity and its parameter controller). Entities also have parameters that describe their physical state, such as the forces applied to them, their velocities, and their locations.

#### Engine
The Game Engine has undergone a significant redesign since the beginning of the project, however its purpose remains unchanged. The Engine is delegated the task by the Game Controller class to update the locations and states of every game object (entity) on every frame. In this sense, the entities (Model) served as an invariant in our design, since it held the most updated state of all of entities in the game. Initially, the engine performed this task by checking if certain user input or collision conditions are met, and moving entities directly by manipulating their X and Y locations. However, this led to the issue of “memory”, where certain desired actions require a behavior that happens over many frames (such as a jumping motion), and the exact behavior is dependent on which frame it is on.

This pushed us to reconsider the design of the Engine as it was not flexible in this way, and alternatives considered 
(such as giving the Engine or entities “memory” of what stage of a movement it has completed) were not desirable. Instead 
we redesigned the Game Engine with the abstraction that the Engine is responsible for two sole tasks, to interpret **what 
needs to be done** on a certain frame to **what objects**, and to actually **perform those actions to those objects**.

In addition, we found that because many elements of platformer/scrolling games implement physics found in real life, it would be a good idea to model our engine’s movement mechanisms with regards to real-world physics as well. As a result we implemented a scheme that models Newtonian mechanics to every single entity (Physics engine). This eliminated the need for “memory”, as a Jumping movement can be defined as an initial upward force combined with Gravity. This creates natural acceleration upward and downward and eliminates the need to define “memory” for every single type of action and the entities that perform them.

The Game Engine’s functionality is made possible by 3 main subcomponents: the Trigger Handler, the Action Applicator, and the Physics Calculator.

* The Trigger Handler is responsible for assembling a list of Trigger objects, which encapsulates entities and the required action on them. This can be thought of as a “To-do List” of actions that need to be done to certain objects. This List is assembled through multiple concrete subclasses of the TriggerHandler class, each responsible for a certain type of “Trigger”. For example, there is a Key Input Trigger Handler that is responsible for determining if actions need to be taken due to user key inputs. Similarly, the Collision Trigger Handler is responsible for determining if actions need to be taken due to any entity collisions that are happening on that certain frame.
* The Action Applicator is responsible for taking the list of Triggers and acting upon them by actually applying the requested actions to the entities. Analogous to the Trigger Handler, this is done through concrete subclasses. For example, there is an action applicator that handles applying an upward normal force, and an action applicator responsible for increasing the score, etc.
* Finally, the Physics Calculator handles the mechanics aspect of the game engine. After all of the forces have been applied to every object through the Action Applicator, the Physics Calculator takes the net force on every entity, and updates its velocity and position based on that net force.

This design of the Game Engine was designed for flexibility in mind. The Trigger Handler and Action Applicator classes are abstract classes that rely on concrete subclasses for specific implementation. In this way, different Trigger Handlers and Action Applicators can be added to the program to expand upon the functionality. As an example, initially we had 2 Trigger Handlers, one that handled entity collisions, and the other that handled user key inputs. When we needed to implement Gravity to our games (universal force that applies to all entities), we decided that it would be best to take advantage of our flexible design for the Game Engine, and add an additional Gravity Trigger Handler to implement this functionality (assigns a downward force to all entities in the game).

#### Game Controller
The Game Controller is responsible for handling the main Game Loop. It keeps track of the timing, and calls upon the Game Engine through its public API to update on each frame. It also handles taking in the user input on each frame (through a finite state machine in the KeyInputMachine class that handles keypress and keyrelease events to support multiple user inputs), and handles pausing of the game. Finally, although excluded in our final submission due to bugs, the Ghost player/AI’s control inputs are also read in at the Game Controller level, and are passed to the Engine along with a user’s key inputs.

### Assumptions and Decisions to Simplify Project Design

#### Frontend
The main assumptions that were made to simplify the design was assuming that only the Game Description View would make use of the xml parsing to create UI components. We assumed that the other screens that required frequent changes such as the main game view should be hard coded using JavaFx techniques. This decision was mainly because a changing screen requires several changes to UI components and this requires a readily dynamic implementation of the front end. Due to reflection while parsing components from XML to screen, it is much difficult to attach changing features such as when an object is moving on a pane to our implementation. This made our code much simpler as we assumed the xml-css implementation would be used for mostly static, non-changing UI scenes.

Also, for the login and signup screens we used manual JavaFx implementations other than required XML-CSS implementation since those screens seem static and are screens that users would prefer to change structural appearance. We evaluated the priority of other game implementations and realized that the team’s priority was to have a good design implementation of Game view rather than an easy to change login or sign up screen. Also, when authenticating information, we required actual data from UI components, but if we had used the xml-css approach we would have needed to figure out how to readily get text entered by the user and this would have taken time so we decided not to use that approach.

#### Backend
Several assumptions were made while designing this project in the backend. First, we assumed that all platformer/scrolling games will have a camera that follows the player in a defined general direction (such as left, right, up, or down). Second, we assumed that these games will have defined objects that shall move around in a 2D game level, with a background and objects that are either stationary or mobile. Originally we had intended there to be a separate class for enemy entities, but this was abstracted to also include stationary objects that do not have a particular “special” behavior. 

All entities also have a predefined “health”. Finally, we assumed that each entity can have a defined interaction with another entity, but in the current implementation of our Game Engine, these are limited to Newtonian forces, increasing the score, and decreasing health of the entity. However these can be expanded upon easily (more below). Finally, we assumed that the goal of the games is to accumulate as many points as possible.

These assumptions helped us narrow our scope for the design of the back-end of our project, but remains flexible which allows for the creation of any type of game that exists within this scope.

### How to Add New Features

#### Additional Levels
Additional Levels are added via XML game file. Each XML level file defines the background image and grid locations for every entity on that level. Please see the ```GameDesignerGuide``` markdown file in the Data folder for more information.

#### Triggers (Causes of Actions)
A new Trigger can be added by first creating a concrete subclass of the TriggerHandler abstract class located in the ```engine``` package. This should follow the engine internal API format, and return a list of Trigger objects.

#### Action Applicators (Reaction to Causes)
A new Action Applicator can be added by creating a concrete subclass of the ActionApplicator abstract class located in the ```engine``` package. This should follow the engine internal API format, and apply some sort of force or action to change the state of entities in a certain way.

Next, a keyword-Action Applicator pairing needs to be established in the ```applicatorInfo.properties``` file located in the ```resources``` package. This allows a keyword to be called through the Trigger Handlers, or “programmed in” through the game file XMLs. To use a new Action Applicator that is triggered by a collision, for example, the new keyword established would need to be used between ```<Interaction>``` tags in either the ```characterConfig``` or ```objects``` XML files.

#### Types of Objects
New types of objects are created entirely through the game XML files. They are defined through the ```objects.xml``` file. Please see the ```GameDesignerGuide``` markdown file in the Data folder for more information.

#### Key Inputs
New types of key inputs can be directly created through the ```characterConfig.xml``` file in the Game folder. These need to be JavaFX Key Event keywords.

#### Collision/Interaction Behaviors
New collision/interaction behaviors can be added in directly through the ```characterConfig``` or ```objects``` XML files. Each character and object has an ```<Interactions>``` section that defines which interactions are active on that object. New interactions can be added here to achieve the desired end-game effect. Please see the ```GameDesignerGuide``` markdown file in the Data folder for more information.

#### AI/Ghost Player
This was a planned feature that was initially included but later removed due to bugs that could not be fixed before the deadline. This feature is where the player can play against an AI, or a recorded game play of their own character. To add in this feature, a BOT character needs to be included in the ```characterConfig.xml``` file, and include Virtual keys as inputs. Next, the ```GhostWriter``` and ```GhostReader``` classes in from the ```bot``` package within the ```controller``` package need to be enabled in the ```GameController``` class. This enables the recording of keystrokes and their timestamp, as well as the reading-in of a file that holds this data to control a non-player controlled character.

### Special Boundaries
This was also a planned feature that we did not have enough time to fully build out before the deadline. Some games such
as Doodle Jump have better experiences when the boundaries are toroidal. This can be achieved easily through reading in the
boundary types through the generalConfig.xml file, and then creating a new type of Trigger Handler that applies forces to objects
to pull or push objects when they reach the boundary of the game level. The keywords used in the XML and the Trigger Handler
will be mapped in a properties file located in the ```applicatorInfo.properties``` file located in the ```resources``` package

#### Frontend
In order to add a new feature using the current design implementation follow these steps:
* XML-CSS approach
    * Decide which view you want to add the UI or view component.
    * Go to the XML associated with that view if XML is present.
    * If XML is not present create one under the view/resources/XML directory
    * Add the UI components in the XML while setting HI component ID using the name section in syntax for xml file (eg. ```<LayoutComponent name="HBox">```)
    * Add ID tag in CSS for the view and specify CSS styling for specific UI components.
* Using JavaFx code
    * Decide where the UI component will be added on view. There are 5 main views so the UI component must be added to one.
    * In the class for the view add a method to set-up the UI component. 
    * Call this new method at the required structural position in the buildscene of the view class.
    * Set ID of UI component to name of choice.
    * Add name tag in CSS and specify its styling.


