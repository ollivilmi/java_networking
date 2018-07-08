package services.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Producer {

    private int port;
    private InetAddress address;
    private DatagramSocket socket;

    public Producer(String address, int port) throws IOException
    {
        this.address = InetAddress.getByName(address);
        this.port = port;
        this.socket = new DatagramSocket();
    }

    public void sendPacket(String message)
    {
        try {
            socket.send(new DatagramPacket(message.getBytes(StandardCharsets.UTF_8), message.length(), address, port));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
