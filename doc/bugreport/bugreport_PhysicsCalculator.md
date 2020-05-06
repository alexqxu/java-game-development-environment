## Description

In games other than the original Super Mario demo game level we created for test purposes, the player's character in the game
will be always stand on the boundary corresponding to what would have been the bottom boundary the Super Mario demo 
level stage even when the game and level being played is a different stage than the Super Mario demo level stage 
(level1.xml in SampleGame1) previously mentioned. 

## Expected Behavior

I expect the player's character to stand on the "floor" (some lower vertical boundary) of the current game level, not
the boundary of a different level.

## Current Behavior

The player's character stands on the vertical boundary - the floor - that corresponds to the original Super Mario
demo game level that our team had created for testing purposes during development. This is an issue because the 
vertical and horizontal boundaries set by the background image of each level even within the same game may not
be the same. 

## Steps to Reproduce

 1. Create a ParameterController object with the default constructor.
 2. Create a new PlayerEntity from a new EntityFactory instance by passing "Player" into the first parameter and
 the ParameterController object just created as the second parameter in the new EntityFactory's getEntity() method.
 3. Set the x and y-acceleration of the PlayerEntity each as a nonzero number.
 4. Set the y-coordinate to a value that would place the PlayerEntity below the lower vertical boundary
 of the Super Mario demo level I described before. 
 5. Initialize a new instance of a class implementing List, declaring its interface type as List<Entity>.
 6. Store the new PlayerEntity object recently created in the List. 
 7. Create a new instance of PhysicsCalculator by passing the List created into the former's constructor.
 8. Invoke the PhysicCalculator's update() method. 

## Failure Logs

N/A

## Hypothesis for Fixing the Bug

To verify the existence of the bug described, I would complete the steps listed above and compare the new y-
acceleration of the PlayerEntity in the List to zero. If the new y-acceleration was zero, this would confirm that 
the PhysicsCalculator object thought that the Player was at or below the lower vertical boundary of the Super Mario demo
level previously mentioned.
I hypothesize that the bug could be fixed by setting the lower vertical boundary of a given level to the lower vertical
boundary of that level's background image, expressed in pixels. 