package names;

import java.util.Objects;

public class Individual
{
    private String name;
    private String gender;
    private int count;
    private int rank;

    public Individual(String name, String gender, int count, int rank)
    {
        this.name = name;
        this.gender = gender;
        this.count = count;
        this.rank = rank;
    }

    public String getName()
    {
        return name;
    }

    public String getGender()
    {
        return gender;
    }

    public int getCount()
    {
        return count;
    }

    public int getRank()
    {
        return rank;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }

        if (other == null || getClass() != other.getClass())
        {
            return false;
        }

        Individual person = (Individual)other;
        return (name.equals(person.name)) && (gender.equals(person.gender)) && (count == person.count);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, gender, count);
    }
}
