package duke.data.exception;

import duke.common.Messages;

public class InvalidValueException extends Exception{

    public InvalidValueException(String message) {
        super(message);
        System.out.println(Messages.MESSAGE_NUMERICAL_ERROR);
    }


}
