import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String INDENT2 = "     ";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String COMMAND_EXIT_STRING = "bye";
    private static final String COMMAND_LIST_STRING = "list";
    private static final String COMMAND_DONE_STRING = "done";
    private static final String COMMAND_DEADLINE_STRING = "deadline";
    private static final String COMMAND_EVENT_STRING = "event";
    private static final String COMMAND_TODO_STRING = "todo";
    private static final String ADDED_MESSAGE = "Got it. I've added this task:\n";
    private static final String MESSAGE_WELL_DONE = "Nice! I've marked this task as done:\n ";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int MAX_CAPACITY = 100;
    public static String taskContent = "";
    private static int taskCount = 0;
    private static int taskIndex = 0;
    private static Task[] tasks = new Task[MAX_CAPACITY];

    public static void main(String[] args) {
        welcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
        }
    }

    static void UiDisplay(String content) {
        System.out.println(INDENT + LINE);
        if (content != null) {
            System.out.println(INDENT + content);
        }
        System.out.println(INDENT + LINE + "\n");
    }

    private static void welcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void addToDoTask(String taskDescription) {
        ToDo t = new ToDo(taskDescription);
        tasks[taskIndex] = t;
//        tasks[taskIndex] = new ToDo(taskDescription);
        addTask(tasks[taskIndex]);
    }

    private static void addDeadlineTask(String taskDescription) {
        int dateSeparator = taskDescription.indexOf("/by");
        String date = taskDescription.substring(dateSeparator);
        tasks[taskIndex] = new Deadline(taskDescription,date);
        addTask(tasks[taskIndex]);
    }

    private static void addEventTask(String taskDescription) {
        int timeSeparator = taskDescription.indexOf("/at");
        String task = taskDescription.substring(0,timeSeparator);
        String eventTime = taskDescription.substring(timeSeparator);
        tasks[taskIndex] = new Deadline(task,eventTime);
        addTask(tasks[taskIndex]);
    }

    private static void addTask(Task content) {
        taskCount++;
        // The next task index wil be increase after assigning the current content.
        tasks[taskIndex++] = content;
        UiDisplay( ADDED_MESSAGE
                + INDENT2 + content
                + "\n    Now you have " + taskCount + " tasks in the list.");
    }

    private static void executeCommand(String userCommand) {
        String taskAction = taskIdentify(userCommand);
        switch (taskAction) {
        case COMMAND_EXIT_STRING:
            exitProgram();
            break;
        case COMMAND_DONE_STRING:
            if (stringIsNumeric(taskContent)) {
                markAsDone(Integer.parseInt(taskContent));
            }
            else {
                System.out.println("Please enter the numeric rank of the task.");
            }
            break;
        case COMMAND_LIST_STRING:
            listProgram();
            break;
        case COMMAND_EVENT_STRING:
            addEventTask(taskContent);
            break;
        case COMMAND_TODO_STRING:
            addToDoTask(taskContent);
            break;
        case COMMAND_DEADLINE_STRING:
            addDeadlineTask(taskContent);
            break;
        default:
            System.out.println("Invalid command.");
            break;
        }
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        return inputLine;
    }

    private static void exitProgram() {
        UiDisplay(MESSAGE_GOODBYE);
        System.exit(0);
    }

    private static void listProgram() {
        String listContent = "";
        int i;
        if (taskCount == 0) {
            listContent =  "You don't have any task in the list now.";
        }
        else {
            for (i = 0; i < taskCount; i++) {
                listContent += "\n" + INDENT + (i+1) + "." + tasks[i];
            }
        }
        UiDisplay(LIST_MESSAGE + listContent);
    }

    private static String taskIdentify(String singleLineCommand) {
        // Checking if is a single word or multiple words used command
        if (singleLineCommand.contains(" ")) {
            String[] splitWords = singleLineCommand.split(" ", 2);
            String taskName = splitWords[0];
            taskContent = splitWords[1];
            return taskName;
        }
        else
            return singleLineCommand;
    }

    private static void markAsDone (int inputNumber) {
        int taskNumber = inputNumber - 1;
        if ((inputNumber > 0) && (taskNumber < taskCount)) {
            tasks[taskNumber].markAsDone();
            UiDisplay(MESSAGE_WELL_DONE + tasks[taskNumber].toString());
        }
        else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Returns true if the given string is a number.
     * Reference from "https://www.baeldung.com/java-check-string-number"
     */
    public static boolean stringIsNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
