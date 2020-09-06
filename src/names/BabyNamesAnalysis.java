package names;

import java.util.*;

public class BabyNamesAnalysis
{
    private DataReader reader;

    public BabyNamesAnalysis(DataReader reader) {
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

    public int differenceInRank(int startYear, int endYear, String name, String gender)
    {
        if (isNameInYear(startYear, name, gender) && isNameInYear(endYear, name, gender))
        {
            int startRank = reader.getYearOfBirthFile(startYear).getIndividual(name, gender).getRank();
            int endRank = reader.getYearOfBirthFile(endYear).getIndividual(name, gender).getRank();
            return startRank - endRank;
        }
        throw new IllegalArgumentException("Name not found");
    }

    private boolean isNameInYear(int year, String name, String gender)
    {
        return reader.getYearOfBirthFile(year).getIndividual(name, gender) != null;
    }

    private boolean isNameInYearRange(int startYear, int endYear, String name, String gender)
    {
        for (int year = startYear; year <= endYear; year++)
        {
            if (!isNameInYear(year, name, gender))
            {
                return false;
            }
        }
        return true;
    }

    public String mostVolatileName(int startYear, int endYear, String gender)
    {
        Map<String, Integer> rankDifferences = new HashMap<>();
        for (Individual person: reader.getIndividualsInRange(startYear, endYear))
        {
            String name = person.getName();
            if (isNameInYear(startYear, name, gender) && isNameInYear(endYear, name, gender))
            {
                rankDifferences.put(name, Math.abs(differenceInRank(startYear, endYear, name, gender)));
            }
        }

        String mostVolatileName = getMaxEntryInMap(rankDifferences).getKey();
        return mostVolatileName;

    }

    private Map.Entry<String, Integer> getMaxEntryInMap(Map<String, Integer> nameMap)
    {
        Map.Entry<String, Integer> entryWithMaxValue = null;

        for (Map.Entry<String, Integer> currentEntry : nameMap.entrySet())
        {
            if (entryWithMaxValue == null || currentEntry.getValue().compareTo(entryWithMaxValue.getValue()) > 0)
            {
                entryWithMaxValue = currentEntry;
            }
        }

        return entryWithMaxValue;
    }

    public double averageRankForName(String name, String gender, int startYear, int endYear)
    {
        if (!isNameInYearRange(startYear, endYear, name, gender))
        {
            throw new IllegalArgumentException("Name not found in all of given years");
        }

        double totalRank = 0;
        double totalYears = 0;
        for (Integer rank: allRankingsRange(name, gender, startYear, endYear).values())
        {
            totalRank += rank;
            totalYears++;
        }

        return totalRank / totalYears;
    }

    public String highestAverageRank(int startYear, int endYear, String gender)
    {

    }
}
