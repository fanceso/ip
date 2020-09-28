package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.data.TaskList;
import duke.data.exception.InvalidCommandException;
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
        } catch (IOException | InvalidCommandException exception) {
            ui.showInvalidFileMessage();
        }
        ui.showWelcomeMessage();
    }

    public void runProgram() {
        do {
            try {
                String userInput = ui.getUserInput();
                Command command = Parser.parseCommand(userInput);
                command.execute(taskList, storage, ui);
            } catch (IOException | InvalidCommandException e) {
                System.out.println("Something went Wrong!");
            }
        } while (!ExitCommand.isExit());
    }

    public static void main(String[] args)  {
        new Duke("data-folder/tasks.txt").run();
    }

}
