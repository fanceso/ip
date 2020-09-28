package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {

    protected static boolean programShutdown;

    public Command() {
        programShutdown = false;
    }

    public abstract void execute(TaskList tasks, FileStorage storage, Ui ui) throws IOException;

    public static boolean isExit() {
        return programShutdown;
    }

}
