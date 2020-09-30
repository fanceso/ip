package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        ui.showFindResult(taskList.tasks,keyword);
    }

}