package names;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

    @Test
    void readDataSet() throws Exception
    {
        DataReader testReading = new DataReader("read_test");
        List<Integer> expectedYears = List.of(1111, 1112, 1113, 1114);
        List<Integer> actualYears = testReading.getYears();
        assertEquals(expectedYears, actualYears);
    }
}