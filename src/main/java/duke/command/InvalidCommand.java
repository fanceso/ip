package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

/** Represents an incorrect or invalid command. Upon execution, produces feedback to the user. */
public class InvalidCommand extends Command {

    public final String feedbackToUser;

    /**
     * Initializes the feedback for invalid command used.
     *
     * @param feedbackToUser used to display an error message
     */
    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Retrieves all tasks which contains some of the specified keywords.
     *
     * @param taskList used for recording all tasks as TaskList object
     * @param storage  used for storing data in local file as FileStorage object
     * @param ui       used for user interface display as Ui object
     */
    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        if (FileStorage.fileLoaded) {
            ui.showCommandResult(feedbackToUser);
        }
    }

}
