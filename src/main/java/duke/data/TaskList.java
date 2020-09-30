package duke.data;

import duke.data.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void taskRemove(int index) {
        tasks.remove(index);
    }

    public void markDone(int index) {
        tasks.get(index).markAsDone();
    }

    public static int getTaskListSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

}
