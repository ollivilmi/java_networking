package services.udp.implementations.mb;

import services.udp.Consumer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class Mailbox extends Consumer {

    private LinkedList<String> messages;

    public Mailbox(int bufferSize, int port) throws IOException {
        super(bufferSize, port);
        messages = new LinkedList<>();
    }

    @Override
    public void handlePacket() {
        String msg = new String(packet.getData(), StandardCharsets.UTF_8);
        System.out.println(msg);

        switch (msg)
        {
            case "empty":
                sendResponse("Emptying mailbox...");
                break;
            case "first":
                sendResponse("First letter: ");
                break;
            case "last":
                sendResponse("Last letter: ");
                break;
            default:
                messages.add(msg);
                sendResponse("Message added to mailbox!");
                break;
        }
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
}
