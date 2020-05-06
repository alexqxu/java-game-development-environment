package ooga.xmlreaders;

import ooga.parser.components.DisplayInfoParser;
import java.util.regex.Pattern;

public class ViewParameterController implements ImmutableViewParameterController{
    private String myName;
    private String myGameName;
    private String myRow;
    private String myColumn;
    private String myImagePath;
    private String myDescription;
    private String myControls;
    private String myLabel;
    private String mySpacing;
    public ViewParameterController(){
        myName ="";
        myRow ="";
        myColumn ="";
        myImagePath ="";
        myDescription="";
        myControls="";
        mySpacing="";
    }

    public void setGameName(String name){
        myGameName = name;
    }
    public void setName(String myName){
        this.myName = myName;
    }

    public void setRow(String row){
        this.myRow = row;
    }

    public void setColumn(String column){
        this.myColumn = column;
    }

    public void setImagePath(String path){
        myImagePath =path;
    }

    public void setLabelText(String label){
        myLabel = label;
    }

    public void setSpacing(String spacing){
        mySpacing = spacing;
    }

    public void setDescription(String description){ myDescription = description;}

    public String getName(){ return myName;}

    public String getImagePath(){
        return myImagePath;
    }
    //TODO Refactor to handle error where row and column are not an integer

    public int getRow(){
        return Integer.parseInt(myRow);
    }

    public String getGameName(){ return myGameName; }

    public int getColumn(){ return Integer.parseInt(myColumn);}

    public String getDescription(){ return myDescription;}

    public String getControls() { return myControls; }

    public String getLabelText(){ return myLabel; }

    public int getSpacing(){  return Integer.parseInt(mySpacing); }

    public void connectDisplayInfoParser(DisplayInfoParser displayParser){
        myGameName = displayParser.getTitle();
        myImagePath = displayParser.getCoverImage();
        myDescription = displayParser.getDescription();
        myControls=displayParser.getControls();
    }

    public void combineParameterController(ImmutableViewParameterController parameterController){
        //TODO: implement later ?
    }

    public boolean hasLabel() {
        return myLabel.length()>0;
    }

    public boolean hasSpacing(){
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(mySpacing).matches();
    }

}
