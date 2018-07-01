import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class DayTimeProtocol {

    public static final int PORT = 13;

    public static void DayTimeClient(String host, int port)
    {
        Socket socket = null;

        try {
            socket = new Socket(host, port);

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

    public static void main(String[] args) {
        DayTimeClient(args[0], PORT);
    }
}
