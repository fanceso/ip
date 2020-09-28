package duke.data.exception;

import duke.common.Messages;
import duke.ui.Ui;

public class InvalidCommandException extends Exception {
    private static Ui uiDisplay = new Ui();

    public InvalidCommandException() {
        uiDisplay.showCommandResult(Messages.MESSAGE_INVALID_COMMAND);
    }

}
