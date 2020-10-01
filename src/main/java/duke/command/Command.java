package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

/** Represents an executable command. */
public abstract class Command {

    protected static boolean programShutdown;

    public Command() {
        programShutdown = false;
    }

    /**
     * Executes the command, displays the result and saves the list of tasks into file.
     *
     * @param taskList used for recording all tasks as TaskList object
     * @param storage  used for storing data in local file as FileStorage object
     * @param ui       used for user interface display as Ui object
     */
    public abstract void execute(TaskList taskList, FileStorage storage, Ui ui);

    public static boolean isExit() {
        return programShutdown;
    }

}
