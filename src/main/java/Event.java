public class Event extends Task {
    protected String eventTime;
    protected final String TASK_TYPE = "[E]";

    public Event(String description, String dateTime) {
        super(description);
        this.eventTime = dateTime;
    }

    @Override
    public String toString() {
        return  String.format(TASK_TYPE + "%s (at: %s)", super.toString(), eventTime);
    }
}