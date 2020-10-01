package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_WELL_DONE;

/** Marking a task as done identified using it's index from Duke. */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private final int taskIndex;

    /**
     * Initializes the task index to be mark as done
     *
     * @param taskIndex Task index to mark done from duke
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the selected task as done, shows feedback messages and updates content to file.
     * Ui will not be activated before local data file is loaded.
     *
     * @param taskList used for recording all tasks as TaskList object
     * @param storage  used for storing data in local file as FileStorage object
     * @param ui       used for user interface display as Ui object
     */
    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        Task task = taskList.getTask(taskIndex);
        taskList.markDone(taskIndex);
        if (FileStorage.fileLoaded) {
            String messageContent = MESSAGE_WELL_DONE + ui.indentPrint() + task;
            ui.showCommandResult(messageContent);
            storage.autoSave(taskList);
        }
    }

}
