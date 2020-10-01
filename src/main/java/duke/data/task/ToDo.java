package duke.data.task;

/** Represents todo type of task in Duke. */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns string format of the todo task.
     *
     * @return Task as string format
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
