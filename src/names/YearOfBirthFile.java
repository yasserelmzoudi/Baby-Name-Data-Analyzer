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
        String name = "";
        String gender = "";
        int count = 0;

        try 
        {
            Path path = Paths.get(BabyNames.class.getClassLoader().getResource(myFile).toURI());
            for (String line : Files.readAllLines(path)) 
            {
                name = line.split(",")[0];
                gender = line.split(",")[1];
                count = Integer.parseInt(line.split(",")[2]);

                Individual person = new Individual(name, gender, count);
                myIndividuals.add(person);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
}
