package duke.data.exception;

import duke.common.Messages;
import duke.ui.Ui;


public class InvalidTaskIndexException extends Exception {
    private static Ui uiDisplay = new Ui();

    public InvalidTaskIndexException() {
        uiDisplay.showCommandResult(Messages.MESSAGE_INVALID_TASK_INDEX);
    }

}