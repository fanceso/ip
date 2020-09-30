package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_REMOVED;

public class DeleteCommand extends Command {

    public static final String COMMAND_DELETE_WORD = "delete";

    int indexForDeletion;

    public DeleteCommand(int indexForDeletion) {
        this.indexForDeletion = indexForDeletion;
    }

    @Override
    public void execute(TaskList tasks, FileStorage storage, Ui ui) {
        Task task = tasks.getTask(indexForDeletion);
        tasks.taskRemove(indexForDeletion);
        String messageContent = MESSAGE_REMOVED + ui.indentPrint() + task;
        ui.showCommandResult(messageContent);
        storage.autoSave(tasks);
    }

}
