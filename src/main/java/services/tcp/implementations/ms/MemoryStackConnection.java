package services.tcp.implementations.ms;

import services.tcp.MessageConnection;

import java.net.Socket;
import java.util.Stack;

public class MemoryStackConnection extends MessageConnection {

    private Stack<String> memory;

    public MemoryStackConnection(Socket socket)
    {
        super(socket);
        memory = new Stack<>();
    }

    /***
     * Pushse or pops messages to a stack.
     * @param message
     */
    @Override
    public void handleMessage(String message) {
        String[] messages = message.split(" ");

        switch (messages[0])
        {
            case "push":
                if (messages.length != 2) {
                    out.println("Requires 2 arguments (arg1 = push arg2 = message)");
                    return;
                }
                memory.push(messages[1]);
                break;
            case "pop":
                if (!memory.empty())
                    out.println(memory.pop());
                else out.println("Stack is empty");
                break;
        }
    }

    @Override
    public void printInstructions() {
        super.printInstructions();
        out.println("quit - quit connection");
        out.println("push - add message to stack");
        out.println("pop - remove message from stack");
    }
}
