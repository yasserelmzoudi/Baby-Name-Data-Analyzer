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
                boolean unchanged = true;

                for (int currentLine = 0; currentLine < readFile.size(); currentLine++)
                {
                    String line = readFile.get(currentLine);

                    String name = line.split(",")[0];
                    String gender = line.split(",")[1];
                    int count = Integer.parseInt(line.split(",")[2]);

                    if (!gender.equals(currentGender) && unchanged)
                    {
                        dataFile.setGenderChangeIndex(currentLine);
                        unchanged = false;
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

    public static void main(String[] args) throws Exception {
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

    }
}
