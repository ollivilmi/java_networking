package servers;

import servers.parent.Server;

import java.net.Socket;

public class ChatServer extends Server {

    public ChatServer(int port)
    {
        super(port);
    }

    @Override
    public void createConnection(Socket socket) {
        ChatConnection connection = new ChatConnection(socket);
        new Thread(connection).start();
    }
}
