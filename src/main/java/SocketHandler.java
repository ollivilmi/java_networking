import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SocketHandler {

    /***
     * Make sure socket is closed gracefully
     * @param s
     */
    public static void close(Socket s)
    {
        try { if (s != null) s.close(); }
        catch (IOException e){}
    }

    /***
     * Returns buffered reader from the socket, which may be used to read
     * lines of characters
     * @param s
     * @return
     * @throws IOException
     */
    public static BufferedReader getBufferedReader(Socket s) throws IOException
    {
        return new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    /***
     * Prints connection information (IP and port) to system.out
     * @param s
     */
    public static void printConnectionInformation(Socket s)
    {
        System.out.println("Connected to " + s.getInetAddress() + " on port " + s.getPort());
    }

    public static PrintStream getPrintStream(Socket s) throws IOException
    {
        return new PrintStream(s.getOutputStream());
    }
}
