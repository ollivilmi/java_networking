import services.DayTimeProtocol;
import services.EchoProtocol;
import services.tcp.Service;
import services.tcp.implementations.ms.MemoryStackServer;
import services.URLFileRetriever;
import services.udp.implementations.mb.LetterServer;
import services.udp.implementations.mb.Mailbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Menu {

    private final BufferedReader USER_INPUT;
    private Stack<Service> services;

    public Menu() {
        USER_INPUT = new BufferedReader(new InputStreamReader(System.in));
    }

    public void open() {
        printServices();
        services = new Stack<>();

        try {
            String input = USER_INPUT.readLine();

            switch (Integer.parseInt(input)) {
                case 1:
                    DayTimeProtocol.dayTimeClient(getHostName());
                    break;
                case 2:
                    EchoProtocol.echoClient(getHostName(), USER_INPUT);
                    break;
                case 3:
                    URLFileRetriever.retrieveFileFromURL(getProtocol(), getHostName(), getFileName());
                    break;
                case 4:
                    services.push(new MemoryStackServer(8205));
                    break;
                case 5:
                    services.push(new Mailbox(1024, 8206));
                    services.push(new LetterServer(8205, 8206, "localhost"));
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
            startServices();
            System.out.println("Press enter to kill services...");
            USER_INPUT.readLine();
            closeServices();

        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startServices()
    {
        for (Service service : services) {
            System.out.println("Starting service: " + service.toString());
            new Thread(service).start();
        }
    }

    private void closeServices()
    {
        for (Service service : services) {
            System.out.println("Closing service: " +service.toString());
            service.end();
        }
        System.exit(0);
    }

    private String getHostName() throws IOException
    {
        System.out.print("Host name: ");
        return USER_INPUT.readLine();
    }

    private String getProtocol() throws IOException
    {
        System.out.print("Protocol: ");
        return USER_INPUT.readLine();
    }

    private String getFileName() throws IOException
    {
        System.out.print("File name: ");
        return USER_INPUT.readLine();
    }

    private void printServices()
    {
        System.out.println("\nChoose a service:\n");

        System.out.println("1. DayTime protocol");
        System.out.println("2. Echo protocol");
        System.out.println("3. URL File retriever");
        System.out.println("4. Memory stack server");
        System.out.println("5. UDP Mailbox server\n");
    }
}
