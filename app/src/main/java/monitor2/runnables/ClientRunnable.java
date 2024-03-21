package monitor2.runnables;


import monitor2.domains.Protocol;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ClientRunnable implements Runnable, Closeable {
    private final SSLSocket clientSocket;

    private final Protocol protocol;

    private BufferedReader in;
    private PrintWriter out;
    private boolean isOnServer;
    private boolean isAuthentified;
    private String randomString;

    public ClientRunnable(SSLSocket clientSocket, Protocol protocol) {
        this.clientSocket = clientSocket;

        this.protocol = protocol;
        this.isOnServer = true;
        this.isAuthentified = false;

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8), true);

        } catch (IOException e) {
            System.out.println("[!] Error ClientRunnable: " + Arrays.toString(e.getStackTrace()));
        }
    }
    private void handleMessage(String msg) {}

    @Override
    public void close() throws IOException {
        try {
            isOnServer = false;
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("[!] Error ClientRunnable.close: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (isOnServer) {
                String msg = in.readLine();
                if (msg != null) {
                    handleMessage(msg);
                }
            }
        } catch (Exception e) {
            System.out.println("[!] Error ClientRunnable.run: " + e.getMessage());
        }
    }
}
