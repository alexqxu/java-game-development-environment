# Guide to Designing Game Data Files
##### Last updated by Alex Xu on 4/30/20

### Required Files (Properties)
This file contains the filepaths for the required files.

### Info
This file contains general game information to display, including title, description, etc.

Example:
```
<Info>
    <CoverImage>images/cover.png</CoverImage>
    <DescriptionImage>images/cover.png</DescriptionImage>
    <Title>Mario: Revenge of Luigi</Title>
    <Description>This is a sample game, used for testing purposes</Description>
    <Controls>Use Arrow Keys to Move</Controls>
</Info>
```

Note: Entries should be Strings.

### Objects
This file defines the types of objects that are available to be placed for level generation.

Example:

```
<Object>
        <Name>Brick</Name>
        <ImagePath>images/brick.gif</ImagePath>
        <Size>
            <Width>1</Width>
            <Height>1</Height>
        </Size>
        <Interactions>
            <Interaction>Character, LEFT, ReduceLives</Interaction>
            <Interaction>Character, RIGHT, ReduceLives</Interaction>
            <Interaction>Character, BOTTOM, ReduceLives</Interaction>
            <Interaction>Character, TOP, ReduceLives</Interaction>
            <Interaction>Brick, LEFT, ReduceLives</Interaction>
            <Interaction>Brick, RIGHT, ReduceLives</Interaction>
            <Interaction>Brick, BOTTOM, ReduceLives</Interaction>
            <Interaction>Brick, TOP, ReduceLives</Interaction>
        </Interactions>
        <Movements>

        </Movements>
    </Object>
```

Notes:

   * object is surrounded by ```<Object>``` Tags
   * Size and dimensions are in grid cells
   * Interactions are surrounded by ```<Interaction>``` tags, and inside should be a 3-term comma delimited string
   expression. The first term represents the collidee object, the middle term represents the collision side with respect
   to the main object, and the last term represents the action called. A set of all available actions can be found in the
   ```applicatorInfo.properties``` file found in the ```resources``` directory.

### Level Files
This file contains the initial location of the objects on each level.

Example:
```
<Level>
    <BackgroundImage>bg.png</BackgroundImage>
    <Cell>
        <Object>Brick</Object>
        <X>3</X>
        <Y>3</Y>
    </Cell>
</Level>
```

Notes:
* The type of object should be in the object tag.
* X and Y location is in terms of Cell location
* X and Y location defines the upper left corner of the object.
* All objects requested to be placed on a level should have their definitions existing in the ```objects.xml``` file.

### Character Configuration
This file contains player information, including controls used.

Example:
```
    <Character>
        <Name>Mario</Name>
        <Image>images/mario_running.gif</Image>
        <Controls>
            <Control>Left, A</Control>
            <Control>Right, D</Control>
            <Control>Up, W</Control>
            <Control>Down, S</Control>
        </Controls>
        <Size>
            <X>7</X>
            <Y>2</Y>
            <Width>1</Width>
            <Height>2</Height>
        </Size>
        <Interactions>
            <Interaction>Brick, LEFT, Right</Interaction>
            <Interaction>Brick, RIGHT, Left</Interaction>
            <Interaction>Brick, TOP, IncreaseScore</Interaction>
            <Interaction>Brick, BOTTOM, Stand</Interaction>
            <Interaction>Brick, TOP, Down</Interaction>
        </Interactions>
    </Character>
```
Notes: 
* Controls are defined to be: ```Action```, ```Key```. Action is the action performed. Key is the keyboard input.
* Size and dimensions are with respect to the grid
* Interactions are defined in the same way as they are for objects (see above).