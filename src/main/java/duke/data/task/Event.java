package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Represents event type of task in Duke. */
public class Event extends Task {
    public LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Returns string format of the event task.
     *
     * @return Task string and formats the event time and date as "MMM dd yyyy HH:mm"
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH)));
    }

}
