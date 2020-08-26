package names;

import java.util.Objects;

public class Individual
{
    private String name;
    private String gender;
    private int count;

    public Individual(String name, String gender, int count)
    {
        this.name = name;
        this.gender = gender;
        this.count = count;
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
