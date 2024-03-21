package monitor2.runnables;

import java.io.IOException;

public class ProbeListenerTaskRunnable implements Runnable {
    private final ProbeListener probeListener;

    public ProbeListenerTaskRunnable(ProbeListener probeListener) {
        this.probeListener = probeListener;
    }

    @Override
    public void run() {
        try {
            probeListener.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
