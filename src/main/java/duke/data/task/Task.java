package duke.data.task;

/** Parent Task class used for all type. */
public abstract class Task {
    public final static String TICK = "\u2713";
    public final static String CROSS = "\u2718";
    public String description;
    public boolean isDone;

    /**
     * Initializes task and setting status of done to be false.
     *
     * @param description used as the task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task status as done which is true. */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets a Unicode character representing if the duke.task.Task is done
     * (tick for yes, cross for no).
     *
     * @return Task status string with UTF-8 icon
     */
    public String getStatusIcon() {
        return (isDone ? "[" + TICK + "]" : "[" + CROSS + "]");
    }

    /**
     * Returns string version of task.
     *
     * @return Task string with its status in UTF-8 icon
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

}