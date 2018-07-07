package servers.parent;

import handlers.SocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public abstract class MessageConnection implements Runnable {

    protected Socket socket;
    protected BufferedReader in;
    protected PrintStream out;

    /***
     * Creates a bi-directional TCP message service that can be used with Telnet
     * on this socket. The handleMessage() must be implemented to process user
     * inputs to this connection service.
     *
     * User input "quit" exits the connection.
     * @param socket
     */
    public MessageConnection(Socket socket) {
        this.socket = socket;
        try
        {
            in = SocketHandler.getBufferedReader(socket);
            out = SocketHandler.getPrintStream(socket);
            out.println(SocketHandler.printConnectionInformation(socket));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Boolean connected = true;
            String message;

            // Dispose of Telnet input garbage
            out.println("Press any button to start...");
            in.readLine();
            printInstructions();

            while (connected)
            {
                out.print(": ");
                message = in.readLine();
                if (message.equals("quit"))
                    connected = false;
                else handleMessage(message);
            }
            out.close();
            in.close();
            SocketHandler.close(socket);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void handleMessage(String message);

    public void printInstructions()
    {
        out.println("Instructions: ");
    }
}
