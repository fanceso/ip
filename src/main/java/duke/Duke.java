package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.FileStorage;
import duke.ui.Ui;

import java.io.IOException;

/** Interactive chat bot for personal task taking. */
public class Duke {

    public static FileStorage storage;
    public static Ui ui;
    public TaskList taskList;

    /**
     * Initializes Duke chat bot.
     *
     * @param filePath is the path to store and load task in local disk
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new FileStorage(filePath);
    }

    public static void main(String[] args) {
        new Duke("data-folder/tasks.txt").run();
    }

    /** Runs Duke chat bot. */
    public void run() {
        initializeDuke();
        runProgram();
        exitProgram();
    }

    /** Exit Duke chat bot and display the actual data file location in disk. */
    private void exitProgram() {
        ui.showFileLocation();
    }

    /** Runs Duke chat bot. */
    public void initializeDuke() {
        try {
            taskList = new TaskList();
            FileStorage.loadFile(taskList);
        } catch (IOException exception) {
            ui.showInvalidFileMessage();
        }
        ui.showWelcomeMessage();
    }

    /**
     * Runs the main program and receives user's input.
     * Executes the commands and terminates the program when receives exit command.
     */
    public void runProgram() {
        boolean isExit;
        do {
            String userInput = ui.getUserInput();
            Command command = Parser.parseCommand(userInput);
            command.execute(taskList, storage, ui);
            isExit = Command.isExit();
        } while (!isExit);
    }

}
