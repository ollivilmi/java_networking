import java.io.*;
import java.net.*;

public class DayTimeProtocol {
    public static final int PORT = 13;

    public static void main(String[] args)
    {
        Socket socket = null;
        String timestamp;

        try {
            socket = new Socket(args[0], PORT);
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            System.out.println("Connected to " + socket.getInetAddress() + " on port " + socket.getPort());

            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            System.out.println(data);
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            // Force connection to close
            try { if (socket != null) socket.close(); }
            catch (IOException e){}
        }
    }
}
