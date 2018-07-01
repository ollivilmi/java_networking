import Services.DayTimeProtocol;
import Services.EchoProtocol;
import Services.URLFileRetriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    private final BufferedReader USER_INPUT;

    public Menu()
    {
        USER_INPUT = new BufferedReader(new InputStreamReader(System.in));
    }

    public void open() {
        printServices();

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
                default:
                    System.out.println("Invalid command");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Invalid command.");
        }
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
        System.out.println("3. URL File retriever\n");
    }
}
