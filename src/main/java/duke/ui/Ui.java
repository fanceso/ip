package duke.ui;

import duke.data.TaskList;
import duke.storage.FileStorage;

import java.io.PrintStream;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_GOODBYE;
import static duke.common.Messages.MESSAGE_INVALID_FILE;
import static duke.common.Messages.MESSAGE_LIST_OUT;
import static duke.common.Messages.MESSAGE_NO_TASK;
import static duke.common.Messages.MESSAGE_NUMBER_OF_TASK;
import static duke.common.Messages.MESSAGE_VERSION;
import static duke.common.Messages.MESSAGE_WELCOME;

public class Ui {
    private Scanner in;
    private PrintStream out;

    public static final String LINE = "-".repeat(Math.max(0, 59));
    public static final String INDENT = "\t";

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

    public void drawlLine() {
        out.println(LINE);
    }

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

    public void showTaskList(TaskList taskList) {
        drawlLine();
        if (taskList.getTaskListSize() > 0) {
            out.print(MESSAGE_LIST_OUT);
            int i;
            for (i = 0; i < taskList.getTaskListSize(); i++) {
                out.println(INDENT + String.format("%d.%s", i + 1, taskList.getTask(i)));
            }
            out.println(String.format(MESSAGE_NUMBER_OF_TASK, i));
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
