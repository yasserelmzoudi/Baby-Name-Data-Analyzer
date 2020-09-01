package names;

import java.util.HashMap;
import java.util.Map;

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
            if (file.getMyYear() == year) {
                result += file.topRankedName("F") + " " + file.topRankedName("M");
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
                result += file.namesLetter(gender, letter).size() + " " + file.totalBabies(gender, letter);
            }
        }

        return result;
    }

    public Map<Integer, Integer> allRankings(String name, String gender)
    {
        Map<Integer, Integer> result = new HashMap<>();

        for (YearOfBirthFile file : reader.getData())
        {
            for (Individual person : file.getMyIndividuals())
            {
                if (name.equals(person.getName()) && gender.equals(person.getGender()))
                {
                    result.putIfAbsent(file.getMyYear(), person.getRank());
                }
            }
        }

        return result;
    }

    public String todayName(String name, String gender, int year)
    {
        Map<Integer, Integer> rankings = allRankings(name, gender);
        int desiredRank = rankings.get(year);

        return reader.getData().get(reader.getData().size() - 1).getNameRank(desiredRank, gender);
    }


    public String mostPopularName(int i, int i1, String f) {
    }
}
