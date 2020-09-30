package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, FileStorage storage, Ui ui) {
        ui.showTaskList(tasks);
    }

}
