package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.data.TaskList;
import duke.data.exception.InvalidCommandException;
import duke.data.exception.InvalidDateFormatException;
import duke.data.exception.InvalidDateTimeFormatException;
import duke.data.exception.InvalidTaskIndexException;
import duke.data.exception.InvalidValueException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Messages.MESSAGE_INVALID_COMMAND;
import static duke.common.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_DATE_TIME_FORMAT;
import static duke.common.Messages.MESSAGE_INVALID_TASK_INDEX;
import static duke.common.Messages.MESSAGE_NUMERICAL_ERROR;

/** Parses user input. */
public class Parser {

    public static String taskContent;
    private static Task tasks;

    /**
     * Parses user input into command for execution.
     *
     * @param userCommand full user input string
     * @return the command based on the user input
     */
    public static Command parseCommand(String userCommand) {
        Command command;
        String taskAction = taskIdentify(userCommand);
        switch (taskAction) {
        case ExitCommand.COMMAND_EXIT_WORD:
        case ExitCommand.COMMAND_BYE_WORD:
            command = new ExitCommand();
            break;
        case ListCommand.COMMAND_WORD:
            if (!taskContent.equals("")) {
                command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
            } else {
                command = new ListCommand();
            }
            break;
        case AddCommand.COMMAND_TODO_WORD:
            try {
                verifyTodoTask(taskContent);
                command = new AddCommand(tasks);
            } catch (InvalidCommandException exception) {
                command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
            }
            break;
        case AddCommand.COMMAND_EVENT_WORD:
            try {
                verifyEventTask(taskContent);
                command = new AddCommand(tasks);
            } catch (InvalidCommandException exception) {
                command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
            } catch (InvalidDateTimeFormatException exception) {
                command = new InvalidCommand(MESSAGE_INVALID_DATE_TIME_FORMAT);
            }
            break;
        case AddCommand.COMMAND_DEADLINE_WORD:
            try {
                verifyDeadlineTask(taskContent);
                command = new AddCommand(tasks);
            } catch (InvalidCommandException exception) {
                command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
            } catch (InvalidDateFormatException exception) {
                command = new InvalidCommand(MESSAGE_INVALID_DATE_FORMAT);
            }
            break;
        case DoneCommand.COMMAND_WORD:
            try {
                // no index input or index is not in integer
                if (stringIsNotNumeric(taskContent) || taskContent.equals("")) {
                    throw new InvalidValueException();
                } else if (Integer.parseInt(taskContent) > TaskList.getTaskListSize()) {
                    throw new InvalidTaskIndexException();
                }
                command = new DoneCommand(Integer.parseInt(taskContent) - 1);
            } catch (InvalidValueException e) {
                command = new InvalidCommand(MESSAGE_NUMERICAL_ERROR);
            } catch (InvalidTaskIndexException | IndexOutOfBoundsException e) {
                command = new InvalidCommand(MESSAGE_INVALID_TASK_INDEX);
            }
            break;
        case DeleteCommand.COMMAND_DELETE_WORD:
            try {
                if (stringIsNotNumeric(taskContent) || taskContent.equals("")) {
                    throw new InvalidValueException();
                } else if (Integer.parseInt(taskContent) > TaskList.getTaskListSize()) {
                    throw new InvalidTaskIndexException();
                }
                command = new DeleteCommand(Integer.parseInt(taskContent) - 1);
            } catch (InvalidValueException e) {
                command = new InvalidCommand(MESSAGE_NUMERICAL_ERROR);
            } catch (InvalidTaskIndexException | IndexOutOfBoundsException e) {
                command = new InvalidCommand(MESSAGE_INVALID_TASK_INDEX);
            }
            break;
        case FindCommand.COMMAND_WORD:
            if (taskContent.equals("")) {
                command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
            } else {
                command = new FindCommand(taskContent);
            }
            break;
        default:
            command = new InvalidCommand(MESSAGE_INVALID_COMMAND);
        }
        return command;
    }

    /**
     * Checking if is a single word or multiple words used command
     *
     * @param singleLineCommand full line input from user
     * @return the first word of the input
     */
    private static String taskIdentify(String singleLineCommand) {
        String singleWord = singleLineCommand.trim();
        if (singleWord.contains(" ")) {
            String[] splitWords = singleWord.split(" ", 2);
            String taskName = splitWords[0];
            taskContent = splitWords[1];
            return taskName;
        }
        taskContent = "";
        return singleWord;
    }

    /**
     * Verify that description of todo task is not empty
     *
     * @throws InvalidCommandException if there no description of the task
     */
    private static void verifyTodoTask(String taskDescription) throws InvalidCommandException {
        if (!taskDescription.equals("")) {
            tasks = new ToDo(taskDescription);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Verify that description of event task is enter correctly
     *
     * @throws InvalidCommandException        if there no description or time of the event task
     * @throws InvalidDateTimeFormatException if the format of date and time for the event task is wrong
     */
    private static void verifyEventTask(String taskDescription) throws InvalidCommandException, InvalidDateTimeFormatException {
        try {
            int timeSeparator = taskDescription.indexOf("/at");
            String task = taskDescription.substring(0, timeSeparator).trim();
            String atTime = taskDescription.substring(timeSeparator + 3).trim();
            if (task.equals("") || atTime.equals("")) {
                throw new InvalidCommandException();
            }
            LocalDateTime eventTime = LocalDateTime.parse(atTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            tasks = new Event(task, eventTime);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new InvalidCommandException();
        } catch (DateTimeParseException exception) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Verify that description of deadline task is enter correctly
     *
     * @throws InvalidCommandException    if there no description or date of the event task
     * @throws InvalidDateFormatException if the format of date for the event task is wrong
     */
    private static void verifyDeadlineTask(String taskDescription) throws InvalidCommandException, InvalidDateFormatException {
        try {
            int timeSeparator = taskDescription.indexOf("/by");
            String task = taskDescription.substring(0, timeSeparator).trim();
            String dueDate = taskDescription.substring(timeSeparator + 3).trim();
            if (task.equals("") || dueDate.equals("")) {
                throw new InvalidCommandException();
            }
            LocalDate deadlineDate = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            tasks = new Deadline(task, deadlineDate);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new InvalidCommandException();
        } catch (DateTimeParseException exception) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Return true if String value can be converted to integer
     *
     * @param str is the second word from input
     * @return true only if string content can be converted to integer
     */
    private static boolean stringIsNotNumeric(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

}