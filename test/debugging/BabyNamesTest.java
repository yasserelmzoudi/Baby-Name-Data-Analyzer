package debugging;

import names.BabyNames;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BabyNamesTest {

    @Test
    void read() throws Exception {
        List<Integer> years = new ArrayList<>();
        years.add(2000);

        BabyNames baby = new BabyNames(years);
        baby.read();
    }
}