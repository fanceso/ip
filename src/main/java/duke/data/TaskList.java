package duke.data;

import duke.data.task.Task;

import java.util.ArrayList;

/** Stores all tasks in ArrayList. */
public class TaskList {
    public static ArrayList<Task> tasks;

    /** Initializes new task ArrayList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public static int getTaskListSize() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void taskRemove(int index) {
        tasks.remove(index);
    }

    /**
     * Marks selected task as done.
     *
     * @param index used as the index of task to mark as done.
     */
    public void markDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Returns the task with the display index in list.
     *
     * @param index used to identify the display index
     * @return Tasks that indexed as the display.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

}
