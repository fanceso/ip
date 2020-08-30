import java.util.Scanner;


public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String COMMAND_EXIT_STRING = "bye";
    private static final String COMMAND_LIST_STRING = "list";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int Capacity = 50;
    private static int contentCount = 0;
    private static String[] contents = new String[Capacity];

    public static void main(String[] args) {
        wellcomeMessage();
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

    private static void wellcomeMessage() {
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

    private static void listProgram() {
        StringBuilder displayContent = new StringBuilder();
        String content;
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null ) {
                if (contents[i + 1]  != null ) {
                    displayContent.append(contents[i] + "\n" + INDENT);
                }
                else{
                    displayContent.append(contents[i]);
                }
            }
        }
        String singleString = displayContent.toString();
        UiDisplay(singleString);
    }

    private static void addContent(String inputContent) {
        contents[contentCount] = inputContent;
        contentCount++;
        UiDisplay("added: " + inputContent);
    }
}
