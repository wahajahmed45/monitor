package monitor2.runnables;



import monitor2.domains.StatusOfTask;
import monitor2.domains.Task;

import java.util.Arrays;

public class ClientTaskHandler implements Runnable {
    private TaskManager taskManager;

    public ClientTaskHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            Task task = taskManager.getNextTask();
            if (task != null) {
                processClientTask(task);
            }
        }
    }

    private void processClientTask(Task task) {
        String[] command = task.getCommand();

        System.out.println("Processing client task with command: " + Arrays.toString(command));

        task.setStatus(StatusOfTask.DONE);
    }
}
