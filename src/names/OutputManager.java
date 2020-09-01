package names;

public class OutputManager
{
    private DataReader reader;

    public OutputManager(DataReader reader) {
        this.reader = reader;
    }

    public String topRanked(int year)
    {
        String result = "";

        for (YearOfBirthFile file : reader.getData())
        {
            if (file.getMyYear() == year) {
                result += "Top Ranked " + year + " Female Name: " + file.topRankedName("F");
                result += "\n";
                result += "Top Ranked " + year + " Male Name: " + file.topRankedName("M");
                result += "\n";
            }
        }

        return result;
    }

    public String nameBabyCount(int year, String gender, char letter)
    {
        String result = "";

        for (YearOfBirthFile file : reader.getData())
        {
            if (file.getMyYear() == year)
            {
                result += "Total " + year + " " + gender + " names starting with '" + letter + "': " + file.namesLetter(gender, letter).size();
                result += "\n";
                result += "Total " + year + " " + gender + " babies whose name Starts with '" + letter + "': " + file.totalBabies(gender, letter);
                result += "\n";
            }
        }

        return result;
    }

    public String allRankings(String name, String gender)
    {
        String result = "";

        for (YearOfBirthFile file : reader.getData())
        {
            for (Individual person : file.getMyIndividuals())
            {
                if (name.equals(person.getName()) && gender.equals(person.getGender()))
                {
                    result += file.getMyYear() + " " + name + " Ranking: " + person.getRank() + "\n";
                }
            }
        }

        return result;
    }

    public String todayName(String name, String gender, int year)
    {
        String result = "";


    }



}
