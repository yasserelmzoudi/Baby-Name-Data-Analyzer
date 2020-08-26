package names;

import java.util.ArrayList;
import java.util.List;

public class YearOfBirthFile
{
    private List<Individual> myIndividuals;
    private int myYear;
    private int genderChangeIndex;

    public YearOfBirthFile(int year)
    {
        myYear = year;
        myIndividuals = new ArrayList<>();
        genderChangeIndex = 0;
    }

    public int getMyYear()
    {
        return myYear;
    }

    public void setGenderChangeIndex(int index)
    {
        genderChangeIndex = index;
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
