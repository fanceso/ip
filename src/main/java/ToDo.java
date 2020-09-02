public class ToDo extends Task {
    protected String dateTime;
    protected final String TASK_TYPE = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.toString();
    }
}