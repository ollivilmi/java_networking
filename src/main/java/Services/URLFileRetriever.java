package Services;

import handlers.FileHandler;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

public class URLFileRetriever {

    /***
     * Retrieves a file from the URL
     * @param host for example graderapp.herokuapp.com
     * @param file for example index.html
     */
    public static void retrieveFileFromURL(String protocol, String host, String file)
    {
        try
        {
            URL url = new URL(protocol +"://" + host + "/" + file);

            System.out.println("\nDownloading from " + url.toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            FileHandler.writeFile(in, file);

            System.out.println("...Download successful. File location: " + System.getProperty("user.dir") + "\\" + Paths.get(file));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
