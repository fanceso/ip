package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.FileStorage;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    public static FileStorage storage;
    public TaskList taskList;
    public static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new FileStorage(filePath);
    }

    public void run() {
        initializeDuke();
        runProgram();
        exitProgram();
    }

    private void exitProgram() {
        ui.showFileLocation();
    }

    public void initializeDuke() {
        try {
            taskList = new TaskList();
            FileStorage.loadFile(taskList);
        } catch (IOException exception) {
            ui.showInvalidFileMessage();
        }
        ui.showWelcomeMessage();
    }

    public void runProgram() {
        boolean isExit = false;
        do {
            String userInput = ui.getUserInput();
            Command command = Parser.parseCommand(userInput);
            command.execute(taskList, storage, ui);
            isExit = command.isExit();
        } while (!isExit);
    }

    public static void main(String[] args) {
        new Duke("data-folder/tasks.txt").run();
    }

}
