package names;

import java.time.Year;
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

    public int[] nameBabyCount(int year, String gender, char letter)
    {
        int [] result = new int[2];

        for (YearOfBirthFile file : reader.getData())
        {
            if (file.getMyYear() == year)
            {
                result[0] = file.namesLetter(gender, letter).size();
                result[1] = file.totalBabies(gender, letter);
            }
        }

        return result;
    }

    public Map<Integer, Integer> allRankings(String name, String gender)
    {
        return allRankingsRange(name, gender, reader.getStartYear(), reader.getEndYear());
    }

    public String todayName(String name, String gender, int year)
    {
        Map<Integer, Integer> rankings = allRankings(name, gender);
        int desiredRank = rankings.get(year);

        return reader.getData().get(reader.getData().size() - 1).getNameRank(desiredRank, gender);
    }


    public List<String> mostPopularName(int startYear, int endYear, String gender)
    {
        List<String> topNames = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (int year = startYear; year <= endYear; year++)
        {
            YearOfBirthFile file = reader.getYearOfBirthFile(year);
            topNames.add(file.topRankedName(gender));
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

    public List<String> mostPopularLetter(int startYear, int endYear)
    {
        Map<String, Integer> letterFrequency = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (int year = startYear; year <= endYear; year++)
        {
            YearOfBirthFile file = reader.getYearOfBirthFile(year);

            for (char letter = 'A'; letter <= 'Z'; letter++)
            {
                letterFrequency.putIfAbsent(letter + "", 0);
                letterFrequency.put(letter + "", letterFrequency.get(letter + "") + file.totalBabies("F", letter));
            }
        }

        int maxLetter = Collections.max(letterFrequency.values());
        String[] keys = letterFrequency.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        List<Individual> peopleFirstLetter = new ArrayList<>();
        for (String key : keys)
        {
            if (letterFrequency.get(key) == maxLetter)
            {
                for (int year = startYear; year <= endYear; year++)
                {
                    YearOfBirthFile file = reader.getYearOfBirthFile(year);
                    peopleFirstLetter.addAll(file.namesLetter("F", key.charAt(0)));
                }
                break;
            }
        }

        for (Individual person : peopleFirstLetter)
        {
            if (!result.contains(person.getName()))
            {
                result.add(person.getName());
            }
        }

        Collections.sort(result);

        return result;
    }

    public Map<Integer, Integer> allRankingsRange(String name, String gender, int startYear, int endYear)
    {
        Map<Integer, Integer> allRankingsRange = new HashMap<>();

        for (YearOfBirthFile file : reader.getFilesInRange(startYear, endYear))
        {
            for (Individual person : file.getMyIndividuals())
            {
                if (name.equals(person.getName()) && gender.equals(person.getGender()))
                {
                    allRankingsRange.putIfAbsent(file.getMyYear(), person.getRank());
                }
            }
        }

        return allRankingsRange;
    }

    public int differenceInRank(int startYear, int endYear, String gender)
    {
        int startRank = reader.getYearOfBirthFile(startYear).get
    }
}
