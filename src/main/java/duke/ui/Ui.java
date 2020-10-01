package duke.ui;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.parser.Parser;
import duke.storage.FileStorage;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static duke.common.Messages.*;

/** Text UI of the application. */
public class Ui {
    public static final String LINE = "-".repeat(Math.max(0, 59));
    public static final String INDENT = "\t";
    private Scanner in;
    private PrintStream out;

    public Ui() {
        this(new Scanner(System.in), System.out);
    }

    public Ui(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public String indentPrint() {
        return INDENT;
    }

    /** Draws line with dashes in terminal */
    public void drawlLine() {
        out.println(LINE);
    }

    /**
     * Gets the input from user and echo out the input back in given format
     *
     * @return fullInputLine which entered by user before line break
     */
    public String getUserInput() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        out.println("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    public void showWelcomeMessage() {
        drawlLine();
        out.println(MESSAGE_VERSION);
        out.println(MESSAGE_WELCOME);
        drawlLine();
    }

    /**
     * Show the filtered result from the keyword used with find with given lists
     *
     * @param tasks   used for finding
     * @param keyword used to match string which contains which is case sensitive
     */
    public void showFindResult(ArrayList<Task> tasks, String keyword) {
        drawlLine();

        // make sure there are tasks in list before filter
        if (tasks.size() > 0) {
            ArrayList<Task> resultList = (ArrayList<Task>) tasks.stream().filter((task) -> (task.description.contains(keyword))).collect(Collectors.toList());
            // there are tasks matches keyword
            if (resultList.size() > 0) {
                out.print(MESSAGE_FOUND);
                resultList.forEach(out::println);
            } else {
                out.println(MESSAGE_NO_SUCH_TASK + Parser.taskContent);
            }
        } else {
            out.println(MESSAGE_NO_TASK);
        }

        drawlLine();

    }

    /** Shows the invalid file message with the file's exact location in disk. */
    public void showInvalidFileMessage() {
        out.println(FileStorage.FILE_FULL_NAME);
        out.println(MESSAGE_INVALID_FILE);
    }

    public void showExitMessage() {
        out.println(MESSAGE_GOODBYE);
    }

    public void showCommandResult(String result) {
        drawlLine();
        out.println(result);
        drawlLine();
    }

    /**
     * Show the list of tasks in terminal
     *
     * @param taskList is the TaskList that used in Duke
     */
    public void showTaskList(TaskList taskList) {
        drawlLine();
        if (taskList.getTaskListSize() > 0) {
            out.print(MESSAGE_LIST_OUT);
            int i;
            for (i = 0; i < taskList.getTaskListSize(); i++) {
                out.println(INDENT + String.format("%d.%s", i + 1, taskList.getTask(i)));
            }
            out.printf((MESSAGE_NUMBER_OF_TASK) + "%n", i);
        } else {
            out.println(MESSAGE_NO_TASK);
        }
        drawlLine();
    }

    public void showFileLocation() {
        out.println("Your file has been saved to:");
        out.println(FileStorage.FILE_FULL_NAME);
        drawlLine();
    }

    public void showError() {
        out.println("Something went wrong");
    }

}
