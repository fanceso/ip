import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n"+ INDENT + "1.[✓] read book\n" + INDENT + "2.[✗] return book\n" + INDENT + "3.[✗] buy bread";
    private static final String COMMAND_EXIT_STRING = "bye";
    private static final String COMMAND_LIST_STRING = "list";
    private static final String MESSAGE_WELL_DONE = "Nice! I've marked this task as done:\n       ";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int Capacity = 50;
    private static int contentCount = 0;
    public static boolean done = false;
    private static String[] contents = new String[Capacity];

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

    private static void executeCommand(String userCommand) {
        switch (userCommand) {
        case COMMAND_LIST_STRING:
            listProgram();
            break;
        case COMMAND_EXIT_STRING:
            exitProgram();
            break;
        default:
            addContent(userCommand);
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

    private static void addContent(String inputContent) {
        contents[contentCount] = inputContent;
        contentCount++;
        UiDisplay("added: " + inputContent);
    }
    private static void listProgram() {
        UiDisplay(LIST_MESSAGE);
        taskIdentify();
    }

    private static void taskIdentify() {
        String userDoneCommand = getUserInput();
        String [] splitWords = userDoneCommand.split(" ", 2);
        String identifyDone = splitWords[0];
        String identifyTask = splitWords[1];
        int taskNumber = Integer.parseInt(identifyTask);
        done = identifyDone.equals("done");
        if (done) {
            taskIsDone (taskNumber);
        }
    }


    private static void taskIsDone (int taskNumber) {
        String taskName;
        switch (taskNumber) {
        case 1:
            taskName = "read book";
            break;
        case 2:
            taskName = "return book";
            break;
        case 3:
            taskName = "buy bread";
            break;
        default:
            taskName = "read book";
            break;
        }
        Task t = new Task(taskName);
        t.markAsDone();
        UiDisplay(MESSAGE_WELL_DONE + t.toString());

    }

}

