package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Represents deadline type of task in Duke. */
public class Deadline extends Task {
    public LocalDate dueDate;

    /**
     * Initializes deadline task.
     *
     * @param description used as the description of deadline task
     * @param dueDate     used as the due date for deadline task
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns string format of the deadline task.
     *
     * @return Task string and formats the due date as "MMM dd yyyy"
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)));
    }

}
