package services.udp.implementations.mb;

import services.tcp.Server;

import java.net.Socket;

public class LetterServer extends Server {

    private int mailBoxPort;
    private String address;

    public LetterServer(int port, int mailBoxPort, String address) {
        super(port);
        this.mailBoxPort = mailBoxPort;
        this.address = address;
    }

    @Override
    public void createConnection(Socket socket) {
        LetterConnection connection = new LetterConnection(socket, address, mailBoxPort);
        Thread thread = new Thread(connection);
        thread.isDaemon();
        thread.start();
    }
}
