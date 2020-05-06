package ooga.parser.frontFacingParsers;

import ooga.parser.components.GeneralInfoParser;

import java.util.List;

/**
 * The purpose of this class is to use composition to extract information necessary for initializing the engine.
 * @author Alex Xu
 */
public class EngineParser {
    public static final String CHARACTER_CONFIG_SUFFIX = "generalConfig.xml";

    private GeneralInfoParser generalInfoParser;

    public EngineParser(String folderPath){
        generalInfoParser = new GeneralInfoParser(folderPath + CHARACTER_CONFIG_SUFFIX);
    }

    public List<String> getBoundaries(){
        return generalInfoParser.getBoundaries();
    }
}
