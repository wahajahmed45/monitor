package monitor2.runnables;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProbeUnicastConnectionRunnable implements Runnable{
    private SSLSocket probeSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ProbeUnicastConnectionRunnable(String probeAddress, int probePort) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            probeSocket = (SSLSocket) sslSocketFactory.createSocket(probeAddress, probePort);
            in = new BufferedReader(new InputStreamReader(probeSocket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(probeSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("[!] Error initializing ProbeConnection: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // always listening for updates
                String receivedData = receiveData();
                if (receivedData != null) {

                    System.out.println("Received data from probe: " + receivedData);
                }

            }
        } catch (Exception e) {
            System.out.println("[!] Error in ProbeConnection: " + e.getMessage());
        } finally {
            close(); // Closing the connection when the thread is interrupted
        }
    }

    public void sendData(String data) {
        out.println(data); // Send data to the probe
    }

    public String receiveData() {
        try {
            return in.readLine(); // Receive data from the probe
        } catch (IOException e) {
            System.out.println("[!] Error receiving data from probe: " + e.getMessage());
            return null;
        }
    }

    public void close() {
        try {
            probeSocket.close(); // Close the socket connection
        } catch (IOException e) {
            System.out.println("[!] Error closing ProbeConnection: " + e.getMessage());
        }
    }
}
