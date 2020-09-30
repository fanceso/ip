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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_FILE_INTRO;

public class FileStorage {

    private static String folderName;
    public static boolean fileLoaded;
    public static String FOLDER_FULL_PATH;
    public static String FILE_FULL_NAME;
    private static Ui ui;

    public FileStorage(String filePath) {
        int divider = filePath.lastIndexOf("/");
        folderName = filePath.substring(0, divider);
        FOLDER_FULL_PATH = System.getProperty("user.dir") + folderName;
        FILE_FULL_NAME = System.getProperty("user.dir") + "\\" + filePath.replace("/", "\\");
        fileLoaded = false;
        ui = new Ui();
    }

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

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_FULL_NAME, StandardCharsets.UTF_8);
        fw.write(MESSAGE_FILE_INTRO + textToAdd);
        fw.close();
    }

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
            System.out.println("There is no record data file found");
        } else {
            if (!fileLoaded) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
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
                            dateTime = LocalDateTime.parse(eventTime, dateFormat);
                            taskDetails = taskDetails.substring(0, atSeparator);
                            taskInFile += "event " + taskDetails
                                    + " /at " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime);
                            break;
                        case 'D':
                            taskDetails = taskDetails.replace("(by:", "/by");
                            taskDetails = taskDetails.replace(")", "");
                            int bySeparator = taskDetails.indexOf("/by");
                            String dueDate = taskDetails.substring(bySeparator + 4);
                            dateTime = LocalDateTime.parse(dueDate, dateFormat);
                            taskDetails = taskDetails.substring(0, bySeparator);
                            taskInFile += "deadline " + taskDetails
                                    + " /by " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime);
                            break;
                        }

                        Parser parser = new Parser();
                        Command command = parser.parseCommand(taskInFile);
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
