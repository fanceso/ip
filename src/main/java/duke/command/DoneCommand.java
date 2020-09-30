package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_WELL_DONE;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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
