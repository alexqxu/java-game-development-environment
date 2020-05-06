package api;

/**
 * Provides functionality to support loading properties and configuration files of various types to initialize
 * various aspects of the game
 */
public interface DataLoaderAPI {
//TODO: Change this API and add to API changes document
    /**
     * Load the resource file and and CSV for the game
     * @param propDir the directory of the resource file
     * @param csvDir the directory of the CSV file
     */
    public void loadGameData(String propDir, String csvDir);

    /**
     * Return the game configuration XML file for the selected game
     * @return the game configuration XML file for the selected game
     */
    public String getGameConfiguration();
}
