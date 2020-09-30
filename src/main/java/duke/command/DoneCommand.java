package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, FileStorage storage, Ui ui) {
        Task task = tasks.getTask(taskIndex);
        tasks.markDone(taskIndex);
        if (FileStorage.fileLoaded) {
            String messageContent = Messages.MESSAGE_WELL_DONE + ui.indentPrint() + task;
            ui.showCommandResult(messageContent);
            storage.autoSave(tasks);
        }
    }

}
