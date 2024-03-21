package monitor2.presentations;

import monitor2.domains.Configuration;
import monitor2.runnables.ProbeListener;
import monitor2.runnables.ProbeListenerTaskRunnable;


import java.io.IOException;

public class MonitorPresenter {
    private final Configuration reader;
    public MonitorPresenter(Configuration read) {
        this.reader = read;
    }

    public void start() throws IOException {
        // Recevoir les annonces mutlicast des probes
        Thread probeListenerTask = new Thread(new ProbeListenerTaskRunnable(new ProbeListener(reader.multicastAddress(), reader.multicastPort())));

        // TODO: Intéragir avec les clients (ajout service, demande état service)
      //  Thread interactionClient = new Thread(new ClientInteractionTask());

        // TODO: Echanger avec les probes
        // Thread exchangeProbe = new Thread(new ProbeInteractionTask());

        // Lancer les threads
        probeListenerTask.start();
        // interactionClient.start();
        // exchangeProbe.start();
    }
}
