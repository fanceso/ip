package duke.common;

public class Messages {
    public static final String LINE = "-".repeat(Math.max(0, 59));
    public static final String INDENT = " ".repeat(Math.max(0, 3));
    public static final String INDENT2 = " ".repeat(Math.max(0, 4));
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String FILE_MESSAGE = "Schedule Action Planner.\n[✘: Pending |  ✓: Done]\nT: To do | E: Event | D: Deadline";
    public static final String LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String COMMAND_BYE_STRING = "bye";
    public static final String COMMAND_EXIT_STRING = "exit";
    public static final String COMMAND_LIST_STRING = "list";
    public static final String COMMAND_DONE_STRING = "done";
    public static final String COMMAND_DELETE_STRING = "delete";
    public static final String COMMAND_DEADLINE_STRING = "deadline";
    public static final String COMMAND_EVENT_STRING = "event";
    public static final String COMMAND_TODO_STRING = "todo";
    public static final String ADDED_MESSAGE = "Got it. I've added this task:\n";
    public static final String MESSAGE_WELL_DONE = "Nice! I've marked this task as done:\n ";
    public static final String DATE_ERROR_MESSAGE =  ":-( OOPS!!! The date format is wrong.";
    public static final String DESCRIPTION_ERROR_MESSAGE = ":-( OOPS!!! The description cannot be empty.";
}
