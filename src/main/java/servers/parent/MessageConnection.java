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
            SocketHandler.printConnectionInformation(socket);
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

            while (connected)
            {
                message = in.readLine();
                if (message.equals("quit"))
                    connected = false;
                else handleMessage(message);
            }
        }
        catch (IOException e)
        {
        }
    }

    public abstract void handleMessage(String message);
}
