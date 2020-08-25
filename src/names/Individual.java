package names;

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

    public int getCount()
    {
        return count;
    }

    @Override
    public String toString()
    {
        return name;
    }


}
