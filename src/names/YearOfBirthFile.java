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

    public void read() throws Exception
    {
        try 
        {
            Path path = Paths.get(BabyNames.class.getClassLoader().getResource(myFile).toURI());
            for (String line : Files.readAllLines(path)) 
            {
                String name = line.split(",")[0];
                String gender = line.split(",")[1];
                int count = Integer.parseInt(line.split(",")[2]);

                Individual person = new Individual(name, gender, count);
                myIndividuals.add(person);
            }
        }
        catch (Exception e)
        {
            throw new Exception("Invalid file", e);
        }
    }

    public void add(Individual person)
    {
        if (!myIndividuals.contains(person))
        {
            myIndividuals.add(person);
        }
        else
        {
            throw new IllegalArgumentException("Individual already accounted for in List");
        }
    }

    public int nameCount(String name, String gender)
    {
        int count = 0;
        for (Individual person: myIndividuals)
        {
            if (person.getName().equals(name) && person.getGender().equals(gender))
            {
                count = person.getCount();
            }
        }
        return count;
    }
}
