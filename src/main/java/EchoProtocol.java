import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class EchoProtocol {

    private static final int PORT = 7;

    public static void echoClient(String host, int port) {

        Socket socket = null;
        try {
            socket = new Socket(host, port);
            BufferedReader in = SocketHandler.getBufferedReader(socket);
            PrintStream out = SocketHandler.getPrintStream(socket);

            SocketHandler.printConnectionInformation(socket);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = "";

            while (!line.equals("quit")) {
                line = "";
                try
                {
                    line = keyboard.readLine();
                    out.println(line);
                    System.out.println(in.readLine());
                }
                catch (IOException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            SocketHandler.close(socket);
        }
    }

    public static void main(String[] args) {
        echoClient(args[0], PORT);
    }
}
