package duke.storage;

import duke.Duke;
import duke.command.Command;
import duke.data.TaskList;
import duke.data.task.Task;
import duke.parser.Parser;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_FILE_INTRO;
import static duke.common.Messages.MESSAGE_FILE_NOT_FOUND;
import static duke.common.Messages.MESSAGE_INVALID_FILE_DATA;

/** Represents the file used to store tasks of Duke data. */
public class FileStorage {

    public static boolean fileLoaded;
    public static String FOLDER_FULL_PATH;
    public static String FILE_FULL_NAME;
    private static String folderName;
    private static Ui ui;

    /**
     * Initializes path of folder and ui, define the exact location in the disk.
     *
     * @param filePath is the full file path used
     */
    public FileStorage(String filePath) {
        int divider = filePath.lastIndexOf("/");
        folderName = filePath.substring(0, divider);
        FOLDER_FULL_PATH = System.getProperty("user.dir") + folderName;
        FILE_FULL_NAME = System.getProperty("user.dir") + "\\" + filePath.replace("/", "\\");
        fileLoaded = false;
        ui = new Ui();
    }

    /**
     * Saves data automatically into file.
     *
     * @param taskList is to save into file.
     */
    public static void autoSave(TaskList taskList) {
        StringBuilder msgContent = new StringBuilder();
        // if file does not exist, create new file
        int i;
        for (i = 0; i < TaskList.getTaskListSize(); i++) {
            msgContent.append("\n\t").append(i + 1).append(".").append(taskList.getTask(i));
        }
        try {
            writeToFile(msgContent.toString());
        } catch (IOException e) {
            ui.showError();
        }
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param textToAdd is the string content to add into file
     * @throws IOException if there were errors writing the data to file
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_FULL_NAME, StandardCharsets.UTF_8);
        fw.write(MESSAGE_FILE_INTRO + textToAdd);
        fw.close();
    }

    /**
     * Loads the data file into {@code TaskList} TaskList .
     *
     * @param taskList is to save into file.
     * @throws IOException if there were errors loading the data to file
     */
    public static void loadFile(TaskList taskList) throws IOException {
        File theDir = new File(folderName);
        File file = new File(FILE_FULL_NAME);

        // create directory if does not exist
        if (!theDir.exists()) {
            System.out.println("Creating new directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                System.out.println("Permission Denied");
            }
            if (result) {
                System.out.println("DIR created");
            }
        }

        String content;

        int lines = 0;
        if (file.createNewFile()) {
            fileLoaded = true;
            System.out.println(MESSAGE_FILE_NOT_FOUND);
        } else {
            if (!fileLoaded) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
                DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
                LocalDate date;
                LocalDateTime dateTime;
                //File reading from UTF-8 charset
                Scanner s = new Scanner(file, StandardCharsets.UTF_8);

                // loading existing file once and add them into list
                boolean completed = false;
                while (s.hasNext()) {
                    // Skipping the first 3 lines of introduction from files
                    if (lines < 3 && !completed) {
                        s.nextLine();
                    } else {
                        completed = true;
                        content = s.nextLine();
                        int dividePoint1 = content.indexOf(".[");
                        int dividePoint2 = content.indexOf("] ");
                        char taskType = content.charAt(dividePoint1 + 2);
                        String taskDetails = content.substring(dividePoint2 + 2);
                        String taskIsDone;
                        String taskInFile = "";
                        taskIsDone = content.substring(dividePoint1 + 5, dividePoint2);
                        switch (taskType) {
                        case 'T':
                            taskInFile += "todo " + taskDetails;
                            break;
                        case 'E':
                            taskDetails = taskDetails.replace("(at:", "/at");
                            taskDetails = taskDetails.replace(")", "");
                            int atSeparator = taskDetails.indexOf("/at");
                            String eventTime = taskDetails.substring(atSeparator + 4);
                            taskDetails = taskDetails.substring(0, atSeparator);
                            try {
                                dateTime = LocalDateTime.parse(eventTime, dateTimeFormat);
                            } catch (DateTimeParseException e) {
                                System.out.println(MESSAGE_INVALID_FILE_DATA);
                                break;
                            }
                            taskInFile += "event " + taskDetails + " /at " + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(dateTime);
                            break;
                        case 'D':
                            taskDetails = taskDetails.replace("(by:", "/by");
                            taskDetails = taskDetails.replace(")", "");
                            int bySeparator = taskDetails.indexOf("/by");
                            String dueDate = taskDetails.substring(bySeparator + 4);
                            try {
                                date = LocalDate.parse(dueDate, dateFormat);
                            } catch (DateTimeParseException e) {
                                System.out.println(MESSAGE_INVALID_FILE_DATA);
                                break;
                            }
                            taskDetails = taskDetails.substring(0, bySeparator);
                            taskInFile += "deadline " + taskDetails + " /by " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
                            break;
                        }

                        // sending converted data as command to program
                        Command command = Parser.parseCommand(taskInFile);
                        command.execute(taskList, Duke.storage, Duke.ui);

                        int taskIndexInFile = lines - 3;
                        if (taskIsDone.equals(Task.TICK)) {
                            taskList.markDone(taskIndexInFile);
                        }
                    }
                    lines++;
                }
                System.out.println("Loaded from local data file: " + FILE_FULL_NAME);
                fileLoaded = true;
            }
        }
    }

}
