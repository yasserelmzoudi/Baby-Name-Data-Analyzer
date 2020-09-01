package names;

import java.util.*;

public class Questions
{
    private DataReader reader;

    public Questions(DataReader reader) {
        this.reader = reader;
    }

    public String[] topRanked(int year)
    {
        String [] result = new String[2];

        for (YearOfBirthFile file : reader.getData())
        {
            if (file.getMyYear() == year) {
                result[0] = file.topRankedName("F");
                result[1] = file.topRankedName("M");
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


    public List<String> mostPopularName(int start, int end, String gender)
    {
        List<String> topNames = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (int year = start; year <= end; year++)
        {
            topNames.add(reader.getYearOfBirthFile(year).topRankedName(gender));
        }

        Set<String> uniqueNames = new HashSet<>(topNames);
        int maxYearsAtTop = -1;

        for (String name : uniqueNames)
        {
            int currentYearsAtTop = Collections.frequency(topNames, name);
            if (currentYearsAtTop > maxYearsAtTop)
            {
                maxYearsAtTop = currentYearsAtTop;
            }
        }

        for (String name : uniqueNames)
        {
            if (maxYearsAtTop == Collections.frequency(topNames, name))
            {
                result.add(name);
            }
        }
        result.add(String.valueOf(maxYearsAtTop));
        return result;
    }

    public List<String> mostPopularName(int start, int end)
    {

    }
}
