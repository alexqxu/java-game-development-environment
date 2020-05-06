package ooga.xmlreaders;

public interface ImmutableViewParameterController {
    public String getName();

    public String getImagePath();
    public int getRow();

    public int getColumn();

    public String getDescription();

    public String getControls();

    public String getGameName();
    public String getLabelText();

    public boolean hasLabel();

    //TODO may remove if unable to set to pane
    public int getSpacing();

    public boolean hasSpacing();
}
