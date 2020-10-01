package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

/**
 * Terminates the program.
 * Tells user where is data file located.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_EXIT_WORD = "exit";
    public static final String COMMAND_BYE_WORD = "bye";

    /**
     * Exits the program, shows feedback messages.
     * Ui will not be activated before local data file is loaded.
     *
     * @param taskList used for recording all tasks as TaskList object
     * @param storage  used for storing data in local file as FileStorage object
     * @param ui       used for user interface display as Ui object
     */
    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        programShutdown = true;
        if (FileStorage.fileLoaded) {
            ui.showExitMessage();
        }
    }

}
