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
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void handleMessage(String message);

    public abstract void printInstructions();
}
