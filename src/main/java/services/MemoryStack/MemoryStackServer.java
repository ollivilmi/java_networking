package services.MemoryStack;

import servers.parent.Server;

import java.net.Socket;

public class MemoryStackServer extends Server {

    public MemoryStackServer(int port)
    {
        super(port);
    }

    @Override
    public void createConnection(Socket socket) {
        MemoryStackConnection connection = new MemoryStackConnection(socket);
        new Thread(connection).start();
    }
}
