package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_EXIT_WORD = "exit";
    public static final String COMMAND_BYE_WORD = "bye";

    @Override
    public void execute(TaskList tasks, FileStorage storage, Ui ui) {
        programShutdown = true;
        // Tells user where is data file located.
        if (FileStorage.fileLoaded) {
            ui.showExitMessage();
        }
    }

}
