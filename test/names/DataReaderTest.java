package names;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

    @Test
    void readDataSet() throws Exception {
        DataReader testReading = new DataReader("read_test");
        List<Integer> expectedYears = List.of(2000);
        List<Integer> actualYears = testReading.getYears();
        assertEquals(expectedYears, actualYears);
    }





}