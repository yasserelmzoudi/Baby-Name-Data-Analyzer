package names;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

  @Test
  void readDataSetFromDirectory() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    List<Integer> expectedYears = List.of(1111, 1112, 1113, 1114, 1115, 1116, 1117);
    List<Integer> actualYears = testReading.getYears();

    assertEquals(expectedYears, actualYears);
  }

  @Test
  void readDataSetFromURL() throws Exception {
    DataReader testReading = new DataReader("read_test", true);
    List<Integer> expectedYears = new ArrayList<>();
    for (int year = 1880; year <= 2018; year++)
    {
      expectedYears.add(year);
    }
    List<Integer> actualYears = testReading.getYears();

    assertEquals(expectedYears, actualYears);
  }
}