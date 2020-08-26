package names;

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
}
