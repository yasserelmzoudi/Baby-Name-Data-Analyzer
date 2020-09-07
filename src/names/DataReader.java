package names;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.*;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class DataReader
{
    private List<Integer> years;
    private List<YearOfBirthFile> data;

    public DataReader(String dataSet) throws Exception {
        years = new ArrayList<>();
        data = new ArrayList<>();
        readDataSet(dataSet);
    }

    public void readDataSet(String dataSet) throws Exception {
        try
        {
            Path path = Paths.get(DataReader.class.getClassLoader().getResource(dataSet).toURI());
            File [] files = path.toFile().listFiles();
            Arrays.sort(files);
            for (File file : files)
            {
                int year = Integer.parseInt(file.getName().substring(3, file.getName().length() - 4));
                years.add(year);

                YearOfBirthFile dataFile = new YearOfBirthFile(year);
                data.add(dataFile);

                List<String> readFile = Files.readAllLines(file.toPath());

                String currentGender = readFile.get(0).split(",")[1];
                boolean unchanged = true;

                for (int currentLine = 0; currentLine < readFile.size(); currentLine++)
                {
                    String line = readFile.get(currentLine);

                    String name = line.split(",")[0];
                    String gender = line.split(",")[1];
                    int count = Integer.parseInt(line.split(",")[2]);

                    if (unchanged && !gender.equals(currentGender))
                    {
                        dataFile.setGenderChangeIndex(currentLine);
                        unchanged = false;
                    }

                    Individual person = new Individual(name, gender, count, currentLine + 1 - dataFile.getGenderChangeIndex(), dataFile);
                    dataFile.add(person);
                }
            }
        }
        catch (Exception e)
        {
            throw new Exception("Invalid file", e);
        }
    }

    public List<Integer> getYears() {
        return years;
    }

    public List<YearOfBirthFile> getData() {
        return data;
    }

    public YearOfBirthFile getYearOfBirthFile(int year)
    {
        for (YearOfBirthFile file : data)
        {
            if (file.getMyYear() == year)
            {
                return file;
            }
        }
        return null;
    }

    public List<YearOfBirthFile> getFilesInRange(int startYear, int endYear)
    {
        List<YearOfBirthFile> filesInRange = new ArrayList<>();
        for (int year = startYear; year <= endYear; year++)
        {
            if (getYears().contains(year))
            {
                filesInRange.add(getYearOfBirthFile(year));
            }
        }

        return filesInRange;
    }

    public int getStartYear()
    {
        Collections.sort(years);
        return years.get(0);
    }

    public int getEndYear()
    {
        Collections.sort(years);
        return years.get(years.size() - 1);
    }

    public Set<Individual> getIndividualsInRange(int startYear, int endYear)
    {
        Set<Individual> individualsInRange = new HashSet<>();
        for (YearOfBirthFile file : getFilesInRange(startYear, endYear))
        {
            individualsInRange.addAll(file.getMyIndividuals());
        }
        return individualsInRange;
    }

    public double nameBabyCountFromLetter(int startYear, int endYear, String gender, char letter)
    {
        double letterCount = 0.0;
        for (YearOfBirthFile file : getFilesInRange(startYear, endYear))
        {
            letterCount += file.totalBabies(gender, letter);
        }
        return letterCount;
    }

    public Set<String> getNamesWithFirstLetter(int startYear, int endYear, String gender, char letter)
    {
        Set<Individual> individualsWithFirstLetter = new HashSet<>();
        Set<String> namesWithFirstLetter = new HashSet<>();

        for (YearOfBirthFile file : getFilesInRange(startYear, endYear))
        {
            individualsWithFirstLetter.addAll(file.namesLetter(gender, letter));
            namesWithFirstLetter.addAll(file.getNamesFromIndividuals(individualsWithFirstLetter));

        }
        return namesWithFirstLetter;
    }
}
