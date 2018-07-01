import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

public class URLFileRetriever {

    private static final String HTTP = "http://";

    /***
     * Retrieves a file from the URL
     * @param host for example graderapp.herokuapp.com
     * @param file for example index.html
     */
    public static void retrieveFileFromURL(String protocol, String host, String file)
    {
        try
        {
            URL url = new URL(protocol + host + "/" + file);

            System.out.println("\nDownloading from " + url.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            String line;
            while ((line = in.readLine()) != null)
                writer.println(line);
            writer.close();

            System.out.println("...Download successful. File location: " + System.getProperty("user.dir") + "\\" + Paths.get(file));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        retrieveFileFromURL(HTTP, args[0], args[1]);
    }
}
