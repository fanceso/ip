package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_REMOVED;

/** Deletes a task identified using it's index from Duke. */
public class DeleteCommand extends Command {

    public static final String COMMAND_DELETE_WORD = "delete";

    int indexForDelete;

    /**
     * Initializes the task index to be deleted.
     *
     * @param indexForDelete Task index to remove from duke
     */
    public DeleteCommand(int indexForDelete) {
        this.indexForDelete = indexForDelete;
    }

    /**
     * Deletes the selected task, shows the feedback message and saves updates content to file.
     *
     * @param taskList used for recording all tasks as TaskList object
     * @param storage  used for storing data in local file as FileStorage object
     * @param ui       used for user interface display as Ui object
     */
    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) {
        Task task = taskList.getTask(indexForDelete);
        taskList.taskRemove(indexForDelete);
        String messageContent = MESSAGE_REMOVED + ui.indentPrint() + task;
        ui.showCommandResult(messageContent);
        storage.autoSave(taskList);
    }

}
