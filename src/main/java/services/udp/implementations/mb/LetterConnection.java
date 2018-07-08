package services.udp.implementations.mb;

import services.tcp.MessageConnection;
import services.udp.Producer;

import java.io.IOException;
import java.net.Socket;

public class LetterConnection extends MessageConnection {

    private Producer udpProcuder;

    /***
     * Creates a bi-directional TCP message service that can be used with Telnet
     * on this socket. The handleMessage() must be implemented to process user
     * inputs to this connection service.
     *
     * User input "quit" exits the connection.
     * @param socket
     */
    public LetterConnection(Socket socket, String address, int port) {
        super(socket);
        try {
            udpProcuder = new Producer(address, port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void handleMessage(String message) {
        udpProcuder.sendPacket(message);
    }
}
