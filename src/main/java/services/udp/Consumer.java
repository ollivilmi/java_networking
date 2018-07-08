package services.udp;

import services.tcp.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public abstract class Consumer implements Service {

    protected DatagramSocket socket;
    protected DatagramPacket packet;
    protected byte[] buffer;
    protected int bufferSize;
    private boolean running;

    public Consumer(int bufferSize, int port) throws IOException
    {
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
    }

    public abstract void handlePacket();
}
