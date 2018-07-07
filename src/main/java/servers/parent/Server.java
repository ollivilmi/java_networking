package servers.parent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {

    public Server(int port)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server now running at port " + port);

            while (true)
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

    public abstract void createConnection(Socket socket);
}
