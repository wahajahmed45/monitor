package monitor2.domains;


import monitor2.runnables.ClientRunnable;
import monitor2.runnables.ProbeListenerTaskRunnable;

public class Task {
    private int id;
    private final String type;
    private ClientRunnable clientRunnable;
    private ProbeListenerTaskRunnable multicastRunnable;
    private final String[] command;
    private StatusOfTask status;
    public Task(int id, String type, ClientRunnable source, String[] command, StatusOfTask status) {
        this.id = id;
        this.type = type;
        this.command = command;
        this.status = status;
        clientRunnable  = source;
    }
    public Task(int id, String type, ProbeListenerTaskRunnable source, String[] command, StatusOfTask status) {
        this.id = id;
        this.type = type;
        this.command = command;
        this.status = status;
        multicastRunnable  = source;
    }

    public String getType() {
        return type;
    }

    public ClientRunnable getSourceClient() {
        return clientRunnable;
    }
    public ProbeListenerTaskRunnable getSourceMultiCast() {
        return multicastRunnable;
    }


    public String[] getCommand() {
        return command;
    }

    public void setStatus(StatusOfTask status) {
        this.status = status;
    }
}
