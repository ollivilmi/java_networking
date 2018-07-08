package services.udp.implementations.mb;

import services.udp.Consumer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class Mailbox extends Consumer {

    private LinkedList<String> letters;

    public Mailbox(int bufferSize, int port) throws IOException {
        super(bufferSize, port);
        letters = new LinkedList<>();
    }

    @Override
    public void handlePacket() {
        String msg = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
        System.out.println(msg);
        letters.add(msg);
        sendResponse("Message added to mailbox!");
    }

    public void sendResponse(String response)
    {
        try {
            socket.send(new DatagramPacket(response.getBytes(StandardCharsets.UTF_8),
                    response.length(), packet.getAddress(), packet.getPort()));
        }
        catch (IOException e)
        {
            System.out.println("Failed to respond to message: " + e.getMessage());
        }
    }

    @Override
    public void end()
    {
        System.out.println("Emptying mailbox...");
        while (!letters.isEmpty())
            System.out.println(letters.pop());
        super.end();
    }
}
