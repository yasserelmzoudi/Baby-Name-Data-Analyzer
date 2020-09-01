package names;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BabyNamesTest {

    @Test
    void readDataSet() throws Exception {
        BabyNames testReading = new BabyNames("ssa_2000s");
        List<Integer> expectedYears = List.of(2000, 2001, 2002);
        List<Integer> actualYears = testReading.getYears();
        assertEquals(expectedYears, actualYears);
    }
}