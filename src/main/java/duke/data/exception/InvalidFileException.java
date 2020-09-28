package duke.data.exception;

import duke.common.Messages;
import duke.ui.Ui;

public class InvalidFileException extends Exception {
    private static Ui uiDisplay = new Ui();

    public InvalidFileException() {
        uiDisplay.showCommandResult(Messages.MESSAGE_INVALID_FILE);
    }

}
