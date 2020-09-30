package duke.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    public LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH)));
    }

}
