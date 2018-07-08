package services.tcp.implementations.ms;

import services.tcp.Server;

import java.net.Socket;

public class MemoryStackServer extends Server {

    public MemoryStackServer(int port)
    {
        super(port);
    }

    @Override
    public void createConnection(Socket socket) {
        MemoryStackConnection connection = new MemoryStackConnection(socket);
        Thread thread = new Thread(connection);
        thread.isDaemon();
        thread.start();
    }
}
