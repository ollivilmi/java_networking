import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class EchoProtocol {

    private static final int PORT = 7;

    public static void echoClient(BufferedReader in, PrintStream out) throws IOException {

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        while (!line.equals("quit"))
        {
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

    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket(args[0], PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());

            SocketHandler.printConnectionInformation(socket);
            echoClient(in, out);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try { if (socket != null) socket.close(); }
            catch (IOException e) {}
        }
    }
}
