package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_ADDED;

/** Adds a task into Duke's list. */
public class AddCommand extends Command {

    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_TODO_WORD = "todo";
    private Task taskToAdd;

    /**
     * Initializes the task to be added.
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    /**
     * Adds a task, shows the feedback message and saves list of tasks into file.
     * Ui will not be activated before local data file is loaded.
     *
     * @param taskList used for recording all tasks as TaskList object
     * @param storage  used for storing data in local file as FileStorage object
     * @param ui       used for user interface display as Ui object
     */
    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        taskList.add(taskToAdd);
        if (FileStorage.fileLoaded) {
            storage.autoSave(taskList);
            String messageContent = MESSAGE_ADDED + ui.indentPrint() + taskToAdd;
            ui.showCommandResult(messageContent);
        }
    }

}
