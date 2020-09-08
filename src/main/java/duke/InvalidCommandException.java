package duke;

public class InvalidCommandException extends Exception {
    protected String errorCode;

    public InvalidCommandException(String errorCode) {
        this.errorCode = errorCode;
    }
}
