package names;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class BabyNames
{
    private List<Integer> years;
    private List<YearOfBirthFile> data;

    public BabyNames(List<Integer> years)
    {
        this.years = years;
        data = new ArrayList<>();

        for (int year: years)
        {
            data.add(new YearOfBirthFile(year));
        }
    }

    public void read() throws Exception
    {
        for (YearOfBirthFile dataFile: data)
        {
            String currentFile = "yob" + dataFile.getMyYear() + ".txt";
            try
            {
                Path path = Paths.get(BabyNames.class.getClassLoader().getResource(currentFile).toURI());
                List<String> readFile = Files.readAllLines(path);
                String currentGender = readFile.get(0).split(",")[1];

                for (int currentLine = 0; currentLine < readFile.size(); currentLine++)
                {
                    String line = readFile.get(currentLine);

                    String name = line.split(",")[0];
                    String gender = line.split(",")[1];
                    int count = Integer.parseInt(line.split(",")[2]);

                    if (!gender.equals(currentGender))
                    {
                        dataFile.setGenderChangeIndex(currentLine);
                    }

                    Individual person = new Individual(name, gender, count);
                    dataFile.add(person);
                }
            }
            catch (Exception e)
            {
                throw new Exception("Invalid file", e);
            }
        }
    }
}
