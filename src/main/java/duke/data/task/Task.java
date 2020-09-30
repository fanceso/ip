package duke.data.task;

public class Task {
    public String description;
    public boolean isDone;
    public static int taskCount = 0;
    public final static String TICK = "\u2713";
    public final static String CROSS = "\u2718";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets a Unicode character representing if the duke.task.Task is done
     * (tick for yes, cross for no).
     */
    public String getStatusIcon() {
        return (isDone ? "[" + TICK + "]" : "[" + CROSS + "]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

}