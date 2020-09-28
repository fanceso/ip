package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {

    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_TODO_WORD = "todo";

    private Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

    @Override
    public void execute(TaskList taskList, FileStorage storage, Ui ui) throws IOException {
        taskList.add(taskToAdd);
        if (FileStorage.fileLoaded) {
            storage.autoSave(taskList);
            String messageContent = Messages.MESSAGE_ADDED + ui.indentPrint() + taskToAdd;
            ui.showCommandResult(messageContent);
        }
    }


}
