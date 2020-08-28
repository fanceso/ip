import java.util.Scanner;

public class Duke {
    private static String LINE = "____________________________________________________________";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String COMMAND_EXIT_STRING = "bye";

    public static void main(String[] args) {
        wellcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
        }
    }
    static void UiDisplay(String content) {
        System.out.println("    " + LINE);
        System.out.println("    " + content);
        System.out.println("    " + LINE);
    }

    private static void wellcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void executeCommand(String userCommand) {
        switch (userCommand) {
        case COMMAND_EXIT_STRING:
            exitProgram();
        default:
            UiDisplay(userCommand);
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


}
