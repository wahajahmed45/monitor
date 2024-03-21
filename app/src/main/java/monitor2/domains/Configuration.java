package monitor2.domains;

import java.util.Map;

public record Configuration(
        String multicastAddress,
        int multicastPort,
        String multicastInterface,
        int clientPort,
        boolean tls,
        String aesKey,
        Map<String, Integer> protocolsDelay,
        Map<String, String> probes
) {

}
