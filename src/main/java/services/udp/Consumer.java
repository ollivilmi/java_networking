package services.udp;

import services.tcp.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public abstract class Consumer implements Service {

    protected DatagramSocket socket;
    protected DatagramPacket packet;
    protected byte[] buffer;
    protected int bufferSize, port;
    private boolean running;

    public Consumer(int bufferSize, int port) throws IOException
    {
        this.port = port;
        this.bufferSize = bufferSize;
        this.buffer = new byte[bufferSize];
        this.packet = new DatagramPacket(new byte[bufferSize], bufferSize);
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        running = true;

        while (running)
        {
            try {
                socket.receive(packet);
                handlePacket();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void end() {
        running = false;
        try {
            new DatagramSocket().send(new DatagramPacket(new byte[1], 1, InetAddress.getByName("localhost"), port));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void handlePacket();
}
