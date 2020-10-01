package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

/** Lists all tasks in the Duke to the user. */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        ui.showTaskList(taskList);
    }

}
