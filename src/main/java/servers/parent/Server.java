package servers.parent;

import handlers.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {

    private boolean running;

    public Server(int port)
    {
        running = true;

        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server now running at port " + port);

            while (running)
            {
                Socket socket = serverSocket.accept();
                createConnection(socket);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void shutDown()
    {
        running = false;
    }

    public abstract void createConnection(Socket socket);
}
