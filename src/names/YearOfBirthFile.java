package names;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class YearOfBirthFile
{
    private List<Individual> myIndividuals;
    private int myYear;
    private String myFile;

    public YearOfBirthFile(int year)
    {
        myYear = year;
        myIndividuals = new ArrayList<>();
        myFile = "yob" + year + ".txt";
    }

    public YearOfBirthFile(int i, String m, char x) {
    }


    public void read()
    {
        try 
        {
            Path path = Paths.get(Main.class.getClassLoader().getResource(myFile).toURI());
            for (String line : Files.readAllLines(path)) 
            {
                Individual person = new Individual(line);
                myIndividuals.add(person);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
}
