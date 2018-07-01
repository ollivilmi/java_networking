package handlers;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {

    public static void writeFile(BufferedReader in, String file) throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(file));

        String line;
        while ((line = in.readLine()) != null)
            writer.println(line);
        writer.close();
    }
}
