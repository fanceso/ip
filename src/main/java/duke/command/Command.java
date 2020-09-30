package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

public abstract class Command {

    protected static boolean programShutdown;

    public Command() {
        programShutdown = false;
    }

    public abstract void execute(TaskList tasks, FileStorage storage, Ui ui);

    public static boolean isExit() {
        return programShutdown;
    }

}
