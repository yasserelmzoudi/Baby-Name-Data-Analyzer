package names;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

                    Individual person = new Individual(name, gender, count, currentLine + 1 - dataFile.getGenderChangeIndex());
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

    /*public static void main(String[] args) throws Exception {
        List<Integer> years = new ArrayList<>();

        //Test Year
        years.add(1111);

        BabyNames baby = new BabyNames(years);
        System.out.println(baby.data.get(0).getMyYear());
        baby.read();

        System.out.println("Top Ranked Female Name: " + baby.data.get(0).topRankedName("F"));
        System.out.println("Gender Switches on Line: " + baby.data.get(0).getGenderChangeIndex() + 1);
        System.out.println("Top Ranked Male Name: " + baby.data.get(0).getNameRank(1, "M"));
        System.out.println("Total Male babies whose Name Starts with 'A': " + baby.data.get(0).totalBabies("M", 'A'));
        System.out.println("Total Names Starting with 'A': " + baby.data.get(0).namesLetter("M", 'A').size());

    }*/
}
