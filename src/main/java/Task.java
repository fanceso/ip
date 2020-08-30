public class Task {
    protected String description;
    protected boolean isDone;
    private static String TICK = "\u2713";
    private static String CROSS = "\u2718";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets a Unicode character representing if the Task is done
     * (tick for yes, cross for no).
     */
    public String getStatusIcon() {
        return (isDone ?  TICK : CROSS);
    }


    public String toString() {
        String boxedChar = "[" + getStatusIcon() + "]";
        return boxedChar + " " + description;
    }
}