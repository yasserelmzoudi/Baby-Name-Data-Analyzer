package names;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main
{
    public static final String DATA_FILE_TEST = "yob1900.txt";
    /**
     * Start of the program.
     */
    public static void main (String[] args)
    {
        try
        {
            Path path = Paths.get(Main.class.getClassLoader().getResource(DATA_FILE_TEST).toURI());
            for (String line: Files.readAllLines(path))
            {

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
