package duke;

import duke.common.Messages;
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
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String FILE_PATH_NAME = System.getProperty("user.dir") + "\\data-folder";
    private static final String FILE_NAME = FILE_PATH_NAME + "\\data.txt";
    public static String taskContent = "";
    private static int taskCount = 0;
    private static boolean loaded = false;
    private static boolean autoSaveMode = false;
    private static File file = new File(FILE_NAME);
    private static ArrayList<Task> tasks = new ArrayList<>();



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
            msgContent += "\n" + Messages.INDENT + (i + 1) + "." + tasks.get(i);
        }
        writeToFile(msgContent);
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, StandardCharsets.UTF_8);
        fw.write(Messages.FILE_MESSAGE + textToAdd);
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

        String content;
        // Skipping the first 3 lines from file
        int lines = 0;
        if (file.createNewFile()) {
            autoSaveMode = true;
            loaded = true;
        } else {
            autoSaveMode = false;
            if (!loaded) {
                //File reading from UTF-8 charset
                Scanner s = new Scanner(file, StandardCharsets.UTF_8);
                // loading existing file once and add them into list
                boolean completed = false;
                while (s.hasNext()) {
                    // ignoring the first 3 lines
                    if (lines < 3 && !completed) {
                        s.nextLine();
                    } else {
                        completed = true;
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
                        int taskIndexInFile = lines - 3;
                        if (taskIsDone.equals(Task.TICK)) {
                            tasks.get(taskIndexInFile).markAsDone();
                        }
                    }
                    lines++;
                }
                System.out.println("Loaded from previous file: " + FILE_NAME);
                loaded = true;
                autoSaveMode = true;
            }
        }
    }

    static void UiDisplay(String content) {
        System.out.println(Messages.INDENT + Messages.LINE);
        if (content != null) {
            System.out.println(Messages.INDENT + content);
        }
        System.out.println(Messages.INDENT + Messages.LINE + "\n");
    }

    private static void welcomeMessage() {
        System.out.println(Messages.LINE);
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println(Messages.LINE);
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
            UiDisplay(Messages.ADDED_MESSAGE
                    + Messages.INDENT2 + content
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
            case Messages.COMMAND_EXIT_STRING:
            case Messages.COMMAND_BYE_STRING:
                exitProgram();
                break;
            case Messages.COMMAND_LIST_STRING:
                listProgram();
                break;
            case Messages.COMMAND_DONE_STRING:
                if (stringIsNumeric(taskContent)) {
                    markAsDone(Integer.parseInt(taskContent));
                } else {
                    System.out.println("Please enter the numeric rank of the task.");
                }
                break;
            case Messages.COMMAND_EVENT_STRING:
                addEventTask(taskContent);
                break;
            case Messages.COMMAND_TODO_STRING:
                addToDoTask(taskContent);
                break;
            case Messages.COMMAND_DEADLINE_STRING:
                addDeadlineTask(taskContent);
                break;
            case Messages.COMMAND_DELETE_STRING:
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
            case "event-error":
            case "deadline-error":
                UiDisplay(Messages.DESCRIPTION_ERROR_MESSAGE);
                break;
            case "deadline-date-error":
            case "event-date-error":
                UiDisplay(Messages.DATE_ERROR_MESSAGE);
                break;
            }
        } catch (IOException exception) {
            UiDisplay(":-( OOPS!!! File Format is wrong.");
        }

    }

    private static String getUserInput() {
        return SCANNER.nextLine();
    }

    private static void exitProgram() {
        UiDisplay(Messages.MESSAGE_GOODBYE);
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
                listContent += "\n" + Messages.INDENT + (i + 1) + "." + tasks.get(i);
            }
            UiDisplay(Messages.LIST_MESSAGE + listContent);
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
                    + Messages.INDENT2 + tasks.get(index)
                    + "\n   Now you have " + taskCount + " tasks in the list.";
            tasks.remove(index);
        }
        UiDisplay(messageContent);

    }

    private static void markAsDone(int inputNumber) throws IOException {
        int taskNumber = inputNumber - 1;
        if ((inputNumber > 0) && (taskNumber < taskCount)) {
            tasks.get(taskNumber).markAsDone();
            UiDisplay(Messages.MESSAGE_WELL_DONE + Messages.INDENT + tasks.get(taskNumber).toString());
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
