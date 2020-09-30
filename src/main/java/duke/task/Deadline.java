package duke.task;

public class Deadline extends Task {
    protected String dueDate;
    protected final String TASK_TYPE = "[D]";

    public Deadline(String description, String date) {
        super(description);
        this.dueDate = date;
    }

    @Override
    public String toString() {
        return String.format(TASK_TYPE + "%s (by: %s)", super.toString(), dueDate);
    }

}