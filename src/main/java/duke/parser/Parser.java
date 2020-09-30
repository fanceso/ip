package duke.parser;

import duke.command.*;
import duke.common.Messages;
import duke.data.TaskList;
import duke.data.exception.InvalidCommandException;
import duke.data.exception.InvalidTaskIndexException;
import duke.data.exception.InvalidValueException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;
import duke.ui.Ui;

public class Parser {

    private static String taskContent;
    private static Task tasks;
    private static Ui ui = new Ui();

    public static Command parseCommand(String userCommand){
        Command command;
        String taskAction = taskIdentify(userCommand);
        switch (taskAction) {
        case ExitCommand.COMMAND_EXIT_WORD:
        case ExitCommand.COMMAND_BYE_WORD:
            command = new ExitCommand();
            break;
        case ListCommand.COMMAND_WORD:
            if (!taskContent.equals("")) {
                command = new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
            } else {
                command = new ListCommand();
            }
            break;
        case AddCommand.COMMAND_TODO_WORD:
            verifyTodoTask(taskContent);
            command = new AddCommand(tasks);
            break;
        case AddCommand.COMMAND_EVENT_WORD:
            try {
                verifyEventTask(taskContent);
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
            command = new AddCommand(tasks);
            break;
        case AddCommand.COMMAND_DEADLINE_WORD:
            try {
                verifyDeadlineTask(taskContent);
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
            command = new AddCommand(tasks);
            break;
        case DoneCommand.COMMAND_WORD:
            try {
                if (!stringIsNumeric(taskContent) || taskContent.equals("")) {
                    throw new InvalidValueException();
                } else if (Integer.parseInt(taskContent) > TaskList.getTaskListSize()) {
                    throw new InvalidTaskIndexException();
                }
                command = new DoneCommand(Integer.parseInt(taskContent) - 1);
            } catch (InvalidValueException e) {
                command = new InvalidCommand(Messages.MESSAGE_NUMERICAL_ERROR);
            } catch (InvalidTaskIndexException | IndexOutOfBoundsException e) {
                command = new InvalidCommand(Messages.MESSAGE_INVALID_TASK_INDEX);
            }
            break;
        case DeleteCommand.COMMAND_DELETE_WORD:
            try {
                if (!stringIsNumeric(taskContent) || taskContent.equals("")) {
                    throw new InvalidValueException();
                } else if (Integer.parseInt(taskContent) > TaskList.getTaskListSize()) {
                    throw new InvalidTaskIndexException();
                }
                command = new DeleteCommand(Integer.parseInt(taskContent) - 1);
            } catch (InvalidValueException e) {
                command = new InvalidCommand(Messages.MESSAGE_NUMERICAL_ERROR);
            } catch (InvalidTaskIndexException | IndexOutOfBoundsException e) {
                command = new InvalidCommand(Messages.MESSAGE_INVALID_TASK_INDEX);
            }
            break;
        default:
            command = new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }
        return command;
    }

    /**
     * Checking if is a single word or multiple words used command
     */
    private static String taskIdentify(String singleLineCommand) {
        singleLineCommand = singleLineCommand.trim();
        if (singleLineCommand.contains(" ")) {
            String[] splitWords = singleLineCommand.split(" ", 2);
            String taskName = splitWords[0];
            taskContent = splitWords[1];
            return taskName;
        }
        taskContent = "";
        return singleLineCommand;
    }

    /**
     * Verify that description of todo task is not empty
     */
    private static void verifyTodoTask(String taskDescription) {
        if (!taskDescription.equals("")) {
            tasks = new ToDo(taskDescription);
        } else {
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
        }
    }

    private static void verifyEventTask(String taskDescription) throws InvalidCommandException {
        try {
            int timeSeparator = taskDescription.indexOf("/at");
            String task = taskDescription.substring(0, timeSeparator).trim();
            String eventTime = taskDescription.substring(timeSeparator + 3).trim();
            if (task.equals("") || eventTime.equals("")) {
                throw new InvalidCommandException();
            }
            tasks = new Event(task, eventTime);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new InvalidCommandException();
        }
    }

    private static void verifyDeadlineTask(String taskDescription) throws InvalidCommandException {
        try {
            int timeSeparator = taskDescription.indexOf("/by");
            String task = taskDescription.substring(0, timeSeparator).trim();
            String dueDate = taskDescription.substring(timeSeparator + 3).trim();
            if (task.equals("") || dueDate.equals("")) {
                throw new InvalidCommandException();
            }
            tasks = new Deadline(task, dueDate);
        } catch (StringIndexOutOfBoundsException iob) {
            throw new InvalidCommandException();
        }
    }

    /* Return true if String value can be converted to integer */
    private static boolean stringIsNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}