package debugging;

import names.BabyNames;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BabyNamesTest {

    @org.junit.jupiter.api.Test
    void read() throws Exception {
        List<Integer> years = new ArrayList<>();
        years.add(2000);

        BabyNames baby = new BabyNames(years);
        baby.read();
    }
}