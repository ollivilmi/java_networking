package handlers;

import java.io.*;
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
    public static String printConnectionInformation(Socket s)
    {
        return "Connected to " + s.getInetAddress() + " on port " + s.getPort();
    }


    /***
     * Returns PrintStream which returns output from the socket as characters
     * @param s
     * @return
     * @throws IOException
     */
    public static PrintStream getPrintStream(Socket s) throws IOException
    {
        return new PrintStream(s.getOutputStream());
    }
}
