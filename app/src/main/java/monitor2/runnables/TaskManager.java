package monitor2.runnables;



import monitor2.domains.StatusOfTask;
import monitor2.domains.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskManager {
    private BlockingQueue<Task> tasks;
    private int idCounter;

    public TaskManager() {
        this.tasks = new LinkedBlockingQueue<>();
        this.idCounter = 0;
    }

    public void createClientTask(ClientRunnable clientRunnable, String[] message) {
        System.out.println("[+] Create client task: " + message[0]);
        tasks.add(new Task(idCounter++, message[0], clientRunnable, message, StatusOfTask.WAITING));
    }

    public void createMulticastTask(ProbeListenerTaskRunnable multicastRunnable, String[] message) {
        System.out.println("[+] Create multicast task: " + message[0]);
        tasks.add(new Task(idCounter++, message[0], multicastRunnable, message, StatusOfTask.WAITING));
    }

    public Task getNextTask() {
        try {
            return tasks.take();
        } catch (Exception e) {
            System.out.println("[!] Error while getting next task");
        }
        return null;
    }

    public int getId() {
        return idCounter;
    }
}
