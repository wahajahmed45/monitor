package monitor2.runnables;



import monitor2.domains.StatusOfTask;
import monitor2.domains.Task;

import java.util.Arrays;

public class Worker implements Runnable {
    private TaskManager taskManager;

    public Worker(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void run() {
        while (true) {
            Task task = taskManager.getNextTask();
            if (task != null) {
                processTask(task);
            }
        }
    }

    private void processTask(Task task) {
        String type = task.getType();
        String[] command = task.getCommand();

        if (type.equals("MULTICAST_TASK")) {
            ProbeListenerTaskRunnable multicastRunnable = (ProbeListenerTaskRunnable) task.getSourceMultiCast();

            System.out.println("Processing multicast task with command: " + Arrays.toString(command));

            task.setStatus(StatusOfTask.DONE);
        } else if (type.equals("UNICAST_TASK")) {
         //   ProbeUnicastConnectionRunnable unicastConnectionRunnable = (ProbeUnicastConnectionRunnable) task.getSourceMultiCast();

           // System.out.println("Processing client task with command: " + Arrays.toString(command));

//            task.setStatus(StatusOfTask.DONE);
        } else {
            System.out.println("Unsupported task type: " + type);

            task.setStatus(StatusOfTask.DONE);
        }
    }
}
