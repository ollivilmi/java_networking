import Services.DayTimeProtocol;
import Services.EchoProtocol;
import Services.URLFileRetriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Choose a service:");

        System.out.println("1. DayTime protocol");
        System.out.println("2. Echo protocol");
        System.out.println("3. URL File retriever");

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            try
            {
                String input = userInput.readLine();
                switch (Integer.parseInt(input))
                {
                    case 1:
                        System.out.print("Host name: ");
                        DayTimeProtocol.DayTimeClient(userInput.readLine());
                        break;
                    case 2:
                        System.out.print("Host name: ");
                        EchoProtocol.echoClient(userInput.readLine(), userInput);
                        break;
                    case 3:
                        System.out.print("Protocol: ");
                        String protocol = userInput.readLine();
                        System.out.print("Host name: ");
                        String host = userInput.readLine();
                        System.out.print("File name: ");
                        String file = userInput.readLine();
                        URLFileRetriever.retrieveFileFromURL(protocol, host, file);
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
                break;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
