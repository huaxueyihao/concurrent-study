package com.concurrent.concurrent_four_state.second.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: huaxueyihao
 * @Version:
 **/
public class AppServer extends Thread {

    private int port;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private final  ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executor.submit(clientHandler);
                clientHandlers.add(clientHandler);

            }

        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    private void dispose() {
        this.clientHandlers.stream().forEach(c -> c.stop());
        this.executor.shutdown();
    }


    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.server.close();
    }


}
