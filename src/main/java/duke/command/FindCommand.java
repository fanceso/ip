package duke.command;

import duke.data.TaskList;
import duke.storage.FileStorage;
import duke.ui.Ui;

/**
 * Finds and lists all tasks in Duke which description contains any of the argument keyword.
 * Keyword used for matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private final String keyword;

    /**
     * Initializes the keyword for finding.
     *
     * @param keyword used to search within the task list in Duke
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
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
        ui.showFindResult(taskList.tasks, keyword);
    }

}