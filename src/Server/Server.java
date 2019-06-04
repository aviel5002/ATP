package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Aviadjo on 3/2/2017.
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private Thread serverThread;
    private ExecutorService tp;


    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        serverThread = new Thread(this::runServer);
        tp = Executors.newFixedThreadPool(Configurations.getThreadPoolNum());
    }


    public void start() {
        serverThread.start();
    }

    private void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    tp.execute(() -> {
                        try {
                            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                            clientSocket.close();
                        } catch (IOException e) {
                            e.getCause();
                        }
                    });
                } catch (SocketTimeoutException e) {
                    e.getCause();
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tp.shutdown();
    }
}