package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH_NAME = System.getProperty("user.dir") + "\\data-folder";
    private static final String FILE_NAME = FILE_PATH_NAME + "\\data.txt";
    private static final String TEMP_FILE_NAME = FILE_PATH_NAME + "\\.temp.data.txt";
    private static final String LINE = "-".repeat(Math.max(0, 59));
    private static final String INDENT = " ".repeat(Math.max(0, 3));
    private static final String INDENT2 = " ".repeat(Math.max(0, 4));
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String FILE_MESSAGE = "Schedule Action Planner.\n[✘: Pending |  ✓: Done]\nT: To do | E: Event | D: Deadline";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String COMMAND_BYE_STRING = "bye";
    private static final String COMMAND_EXIT_STRING = "exit";
    private static final String COMMAND_LIST_STRING = "list";
    private static final String COMMAND_DONE_STRING = "done";
    private static final String COMMAND_DELETE_STRING = "delete";
    private static final String COMMAND_DEADLINE_STRING = "deadline";
    private static final String COMMAND_EVENT_STRING = "event";
    private static final String COMMAND_TODO_STRING = "todo";
    private static final String ADDED_MESSAGE = "Got it. I've added this task:\n";
    private static final String MESSAGE_WELL_DONE = "Nice! I've marked this task as done:\n ";
    private static final Scanner SCANNER = new Scanner(System.in);
    public static String taskContent = "";
    private static int taskCount = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskIndex = 0;
    private static boolean loaded = false;
    private static boolean autoSaveMode = false;
    //    private static final Task[] tasks = new Task[MAX_CAPACITY];
    private static File file = new File(FILE_NAME);


    public static void main(String[] args) throws IOException {
        loadFile();
        System.out.println("Data Directory located: " + FILE_PATH_NAME);
        welcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
        }
    }


    private static void autoSave() throws IOException {
        String msgContent = "";
        // if file does not exist, create new file
        int i;
        for (i = 0; i < taskCount; i++) {
            msgContent += "\n" + INDENT + (i + 1) + "." + tasks.get(i);
        }
        writeToFile(msgContent);
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, StandardCharsets.UTF_8);
        fw.write(FILE_MESSAGE + textToAdd);
        fw.close();
    }

    private static void loadFile() throws IOException {

        File theDir = new File("data-folder");

        // create directory if does not exist
        if (!theDir.exists()) {
            System.out.println("Creating new directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                System.out.println("Permission Denited");
            }
            if (result) {
                System.out.println("DIR created");
            }
        }

        String content ;
        // Skipping the first 3 lines from file
        int lines = 0;
        if (file.createNewFile()) {
            autoSaveMode = true;
            loaded = true;
        } else {
            autoSaveMode = false;
            if (!loaded) {
                //File reading from UTF-8 charset
                Scanner s = new Scanner(file, "UTF8");
                // loading existing file once and add them into list
                while (s.hasNext()) {
                    // ignoring the first 3 lines
                    if (lines < 3) {
                        s.nextLine();
                        lines++;
                    } else {
                        content = s.nextLine();
                        int dividePoint1 = content.indexOf(".[");
                        int dividePoint2 = content.indexOf("] ");
                        char taskType = content.charAt(dividePoint1 + 2);
                        String taskDetails = content.substring(dividePoint2 + 2);
                        String taskIsDone = content.substring(dividePoint1 + 5, dividePoint2);
                        String message = "";
                        switch (taskType) {
                        case 'T':
                            message += "todo " + taskDetails;
                            break;
                        case 'E':
                            taskDetails = taskDetails.replace("(at:", "/at");
                            taskDetails = taskDetails.replace(")", "");
                            message += "event " + taskDetails;
                            break;
                        case 'D':
                            taskDetails = taskDetails.replace("(by:", "/by");
                            taskDetails = taskDetails.replace(")", "");
                            message += "deadline " + taskDetails;
                            break;
                        }
                        executeCommand(message);
                        int taskIndexInFile = taskIndex;
                        if (taskIsDone.equals(Task.TICK)) {
                            tasks.get(taskIndexInFile).markAsDone();
                        }
                    }
                }
                System.out.println("Loaded from previous file: " + FILE_NAME);
                loaded = true;
                autoSaveMode = true;
            }
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
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void addToDoTask(String taskDescription) throws InvalidCommandException {
        if (taskDescription.equals("")) {
            throw new InvalidCommandException("todo-error");
        } else {
            tasks.add(new ToDo(taskDescription));
            addTask(taskDescription);
        }
    }

    private static void addDeadlineTask(String taskDescription) throws InvalidCommandException, StringIndexOutOfBoundsException {
        try {
            int dateSeparator = taskDescription.indexOf("/by");
            String task = taskDescription.substring(0, dateSeparator).trim();
            String date = taskDescription.substring(dateSeparator + 3).trim();
            if (task.equals("")) {
                throw new InvalidCommandException("deadline-error");
            } else if (date.equals("")) {
                throw new InvalidCommandException("deadline-date-error");
            }
            tasks.add(new Deadline(task, date));
            addTask(task);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new InvalidCommandException("deadline-error");
        }
    }

    private static void addEventTask(String taskDescription) throws InvalidCommandException, StringIndexOutOfBoundsException {
        try {
            int timeSeparator = taskDescription.indexOf("/at");
            String task = taskDescription.substring(0, timeSeparator).trim();
            String eventTime = taskDescription.substring(timeSeparator + 3).trim();
            if (task.equals("")) {
                throw new InvalidCommandException("event-error");
            } else if (eventTime.equals("")) {
                throw new InvalidCommandException("event-date-error");
            }
            tasks.add(new Event(task, eventTime));
            addTask(task);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new InvalidCommandException("event-error");
        }
    }

    private static void addTask(String content) {
        taskCount++;
        if (loaded) {
            UiDisplay(ADDED_MESSAGE
                    + INDENT2 + content
                    + "\n   Now you have " + taskCount + " tasks in the list.");
        }
        if (autoSaveMode) {
            try {
                autoSave();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void executeCommand(String userCommand) {
        String taskAction = taskIdentify(userCommand);
        try {
            switch (taskAction) {
            case COMMAND_EXIT_STRING:
            case COMMAND_BYE_STRING:
                exitProgram();
                break;
            case COMMAND_DONE_STRING:
                if (stringIsNumeric(taskContent)) {
                    markAsDone(Integer.parseInt(taskContent));
                } else {
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
            case COMMAND_DELETE_STRING:
                if (stringIsNumeric(taskContent)) {
                    delete(Integer.parseInt(taskContent));
                    autoSave();
                } else {
                    System.out.println("Please enter the numeric rank of the task.");
                }
                break;
            default:
                throw new InvalidCommandException("common");
            }
        } catch (InvalidCommandException exception) {
            switch (exception.errorCode) {
            case "common":
                UiDisplay(":-( OOPS!!! I'm sorry, but I don't know what that means.");
                break;
            case "todo-error":
                UiDisplay(" :-( OOPS!!! The description of todo cannot be empty.");
                break;
            case "deadline-error":
                UiDisplay(":-( OOPS!!! The description of deadline cannot be empty.");
                break;
            case "deadline-date-error":
                UiDisplay(":-( OOPS!!! The date of deadline is wrong format.");
                break;
            case "event-error":
                UiDisplay(":-( OOPS!!! The description of event cannot be empty.");
                break;
            case "event-date-error":
                UiDisplay(":-( OOPS!!! The date of event is wrong format.");
                break;
            }
        } catch (IOException exception) {
            UiDisplay(":-( OOPS!!! File Format is wrong.");

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

    // Display content that has been recorded
    private static void listProgram() {
        String listContent = "";
        int i;
        if (taskCount == 0) {
            listContent = "You don't have any task in the list now.";
            UiDisplay(listContent);
        } else {
            for (i = 0; i < taskCount; i++) {
                listContent += "\n" + INDENT + (i + 1) + "." + tasks.get(i);
            }
            UiDisplay(LIST_MESSAGE + listContent);
        }
    }

    private static String taskIdentify(String singleLineCommand) {
        singleLineCommand = singleLineCommand.trim();
        // Checking if is a single word or multiple words used command
        if (singleLineCommand.contains(" ")) {
            String[] splitWords = singleLineCommand.split(" ", 2);
            String taskName = splitWords[0];
            taskContent = splitWords[1];
            return taskName;
        } else {
            taskContent = "";
            return singleLineCommand;
        }
    }

    private static void delete(int taskNumber) {
        int index = taskNumber - 1;
        String messageContent;
        if (taskNumber > taskCount || taskCount == 0) {
            messageContent = "Fail to delete! You don't have any task in the list now.";
        } else {
            taskCount--;
            messageContent = "Noted. I've removed this task: \n"
                    + INDENT2 + tasks.get(index)
                    + "\n   Now you have " + taskCount + " tasks in the list.";
            tasks.remove(index);

        }
        UiDisplay(messageContent);

    }

    private static void markAsDone(int inputNumber) throws IOException {
        int taskNumber = inputNumber - 1;
        if ((inputNumber > 0) && (taskNumber < taskCount)) {
            tasks.get(taskNumber).markAsDone();
            UiDisplay(MESSAGE_WELL_DONE + INDENT + tasks.get(taskNumber).toString());
            autoSave();
        } else {
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
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
