package Services;

import handlers.SocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class DayTimeProtocol {

    public static void DayTimeClient(String host)
    {
        Socket socket = null;

        try {
            socket = new Socket(host, 13);

            BufferedReader reader = SocketHandler.getBufferedReader(socket);
            SocketHandler.printConnectionInformation(socket);

            System.out.println("Daytime: " + reader.readLine());
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            SocketHandler.close(socket);
        }
    }
}
