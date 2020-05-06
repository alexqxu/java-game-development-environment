package ooga.utilities.exception;

import java.io.FileNotFoundException;

public class WinnerListFileNotFoundException extends RuntimeException {

    public WinnerListFileNotFoundException(String message) {
        super(message);
    }
}
