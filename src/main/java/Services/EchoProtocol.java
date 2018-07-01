package Services;

import handlers.SocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class EchoProtocol {

    public static void echoClient(String host, BufferedReader userInput) {

        Socket socket = null;
        try {
            socket = new Socket(host, 7);
            BufferedReader in = SocketHandler.getBufferedReader(socket);
            PrintStream out = SocketHandler.getPrintStream(socket);

            SocketHandler.printConnectionInformation(socket);

            String line = "";

            while (!line.equals("quit")) {
                line = "";
                try
                {
                    line = userInput.readLine();
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

}
