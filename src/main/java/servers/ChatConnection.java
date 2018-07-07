package servers;

import servers.parent.MessageConnection;

import java.net.Socket;
import java.util.Stack;

public class ChatConnection extends MessageConnection {

    private Stack<String[]> memory;

    public ChatConnection(Socket socket)
    {
        super(socket);
        memory = new Stack<>();
    }

    @Override
    public void handleMessage(String message) {
        String[] messages = message.split(" ");
        if (messages.length < 1)
            return;

        switch (messages[0])
        {
            case "push":
                memory.push(messages);
                break;
            case "pop":
                out.print(memory.pop());
                break;
        }
    }
}
