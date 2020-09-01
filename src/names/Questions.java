package names;

public class Questions
{
    private DataReader reader;

    public Questions(DataReader reader) {
        this.reader = reader;
    }

    public String topRanked(int year)
    {
        String result = "";

        for (YearOfBirthFile file : reader.getData())
        {
            result += "Top Ranked " + file.getMyYear() + " Female Name: " + file.topRankedName("F");
            result += "\n";
            result += "Top Ranked " + file.getMyYear() + " Male Name: " + file.topRankedName("M");
        }

        return result;
    }

    public String namesLetter(int year, String gender, char letter)
    {
        String result = "";


    }



}
