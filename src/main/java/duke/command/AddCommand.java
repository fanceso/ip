package duke.command;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.FileStorage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_ADDED;

public class AddCommand extends Command {

    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_TODO_WORD = "todo";
    private Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }

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
