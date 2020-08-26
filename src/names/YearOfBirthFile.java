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

    public YearOfBirthFile(int year)
    {
        myYear = year;
        myIndividuals = new ArrayList<>();
    }

    public int getMyYear()
    {
        return myYear;
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

    public int oppositeGenderStart()
}
