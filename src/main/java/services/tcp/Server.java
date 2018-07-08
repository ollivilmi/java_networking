package services.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server implements Service {

    private int port;
    private boolean running;

    public Server(int port)
    {
        this.port = port;
    }

    @Override
    public void run() {
        running = true;

        try
        {
            ServerSocket serverSocket = new ServerSocket(port);

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

    @Override
    public void end()
    {
        running = false;
        try {
            new Socket().connect(new InetSocketAddress("localhost", port));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void createConnection(Socket socket);
}
