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

    public List<Individual> getMyIndividuals()
    {
        return myIndividuals;
    }

    public int getMyYear()
    {
        return myYear;
    }

    public int getGenderChangeIndex()
    {
        return genderChangeIndex;
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

    public String getNameRank(int rank, String gender)
    {
        int desiredIndex = rank - 1;

        if (!myIndividuals.get(0).getGender().equals(gender))
        {
            desiredIndex += genderChangeIndex;
        }
        return myIndividuals.get(desiredIndex).getName();
    }

    public String topRankedName(String gender)
    {
        return getNameRank(1, gender);
    }

    public List<Individual> namesLetter(String gender, char letter)
    {
        List<Individual> people = new ArrayList<>();
        for (Individual person: myIndividuals)
        {
            String name = person.getName();
            if (name.charAt(0) == letter && person.getGender().equals(gender))
            {
                people.add(person);
            }
        }
        return people;
    }

    public int totalBabies(String gender, char letter)
    {
        return totalCount(namesLetter(gender, letter));
    }

    public int totalCount(List<Individual> people)
    {
        int totalCount = 0;
        for (Individual person: people)
        {
            totalCount += person.getCount();
        }
        return totalCount;
    }

}
