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

    public Individual getIndividual(String name, String gender)
    {
        Individual desiredIndividual = new Individual(name, gender);

        Individual foundIndividual = null;

        if (myIndividuals.indexOf(desiredIndividual) != -1)
        {
            foundIndividual = myIndividuals.get(myIndividuals.indexOf(desiredIndividual));
        }
        return foundIndividual;
    }

    public void setGenderChangeIndex(int index)
    {
        genderChangeIndex = index;
    }

    public boolean isNameInYear(String name, String gender)
    {
        return getIndividual(name, gender) != null;
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
            totalCount += person.getOccurrences();
        }
        return totalCount;
    }
}
