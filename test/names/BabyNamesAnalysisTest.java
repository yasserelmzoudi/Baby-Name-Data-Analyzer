package names;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BabyNamesAnalysisTest {

  @Test
  void topRanked_standard() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q1 = new BabyNamesAnalysis(testReading);

    String[] expectedRank = {"Isabella", "Alex"};
    String[] actualRank = q1.topRanked(1111);

    assertArrayEquals(expectedRank, actualRank);
  }

  @Test
  void topRanked_standard2() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q1 = new BabyNamesAnalysis(testReading);

    String[] expectedRank = {"Imma", "Aohn"};
    String[] actualRank = q1.topRanked(1112);

    assertArrayEquals(expectedRank, actualRank);
  }

  @Test
  void topRanked_standard3() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q1 = new BabyNamesAnalysis(testReading);

    String[] expectedRank = {"Imma", "Aohn"};
    String[] actualRank = q1.topRanked(1113);

    assertArrayEquals(expectedRank, actualRank);
  }

  @Test
  void topRanked_fromURL() throws Exception {
    DataReader testReading = new DataReader("read_test", true);
    BabyNamesAnalysis q1 = new BabyNamesAnalysis(testReading);

    String[] expectedRank = {"Mary", "John"};
    String[] actualRank = q1.topRanked(1880);

    assertArrayEquals(expectedRank, actualRank);
  }

  @Test
  void nameBabyCount_male() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q2 = new BabyNamesAnalysis(testReading);

    int[] expectedNameBabyCount = {2, 70};
    int[] actualNameBabyCount = q2.nameBabyCount(1111, "M", 'A');

    assertArrayEquals(expectedNameBabyCount, actualNameBabyCount);
  }

  @Test
  void nameBabyCount_female() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q2 = new BabyNamesAnalysis(testReading);

    int[] expectedNameBabyCount = {2, 40};
    int[] actualNameBabyCount = q2.nameBabyCount(1111, "F", 'I');

    assertArrayEquals(expectedNameBabyCount, actualNameBabyCount);
  }

  @Test
  void nameBabyCount_male2_lowercase() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q2 = new BabyNamesAnalysis(testReading);

    int[] expectedNameBabyCount = {1, 20};
    int[] actualNameBabyCount = q2.nameBabyCount(1111, "m", 'y');

    assertArrayEquals(expectedNameBabyCount, actualNameBabyCount);
  }

  @Test
  void allRankings_male() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q3 = new BabyNamesAnalysis(testReading);

    Map<Integer, Integer> expectedRankings = new HashMap<>();
    expectedRankings.put(1111, 1);
    expectedRankings.put(1112, 2);
    expectedRankings.put(1113, 2);
    expectedRankings.put(1114, 2);
    expectedRankings.put(1115, 2);
    expectedRankings.put(1116, 3);
    Map<Integer, Integer> actualRankings = q3.allRankings("Alex", "M");

    assertEquals(expectedRankings, actualRankings);
  }

  @Test
  void allRankings_female() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q3 = new BabyNamesAnalysis(testReading);

    Map<Integer, Integer> expectedRankings = new HashMap<>();
    expectedRankings.put(1111, 1);
    expectedRankings.put(1112, 2);
    expectedRankings.put(1113, 2);
    expectedRankings.put(1114, 2);
    expectedRankings.put(1115, 2);
    expectedRankings.put(1116, 2);
    expectedRankings.put(1117, 2);
    Map<Integer, Integer> actualRankings = q3.allRankings("Isabella", "F");

    assertEquals(expectedRankings, actualRankings);
  }

  @Test
  void allRankings_noName() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q3 = new BabyNamesAnalysis(testReading);

    Map<Integer, Integer> expectedRankings = new HashMap<>();
    Map<Integer, Integer> actualRankings = q3.allRankings("", "M");

    assertEquals(expectedRankings, actualRankings);
  }

  @Test
  void todayName_male_standard() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q4 = new BabyNamesAnalysis(testReading);

    String expectedTodayName = "Flex";
    String actualTodayName = q4.todayName("Aohn", "M", 1111);

    assertEquals(expectedTodayName, actualTodayName);
  }

  @Test
  void todayName_female_standard() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q4 = new BabyNamesAnalysis(testReading);

    String expectedTodayName = "Imma";
    String actualTodayName = q4.todayName("Isabella", "F", 1111);

    assertEquals(expectedTodayName, actualTodayName);
  }

  @Test
  void mostPopularNames() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q5 = new BabyNamesAnalysis(testReading);

    Map<String, Double> expectedMostPopularNames = new HashMap<>();
    expectedMostPopularNames.put("Imma", 3.0);
    Map<String, Double> actualMostPopularNames = q5.mostPopularNames(1111, 1114, "F");

    assertEquals(expectedMostPopularNames, actualMostPopularNames);
  }

  @Test
  void mostPopularLetter() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q6 = new BabyNamesAnalysis(testReading);

    Set<String> expectedMostPopularLetter = Set.of("Imma", "Isabella");
    Set<String> actualMostPopularLetter = q6.mostPopularLetter(1111, 1114);

    assertEquals(expectedMostPopularLetter, actualMostPopularLetter);
  }

  @Test
  void allRankingsRange() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q7 = new BabyNamesAnalysis(testReading);

    Map<Integer, Integer> expectedRankings = new HashMap<>();
    expectedRankings.put(1112, 2);
    expectedRankings.put(1113, 2);
    Map<Integer, Integer> actualRankings = q7.allRankingsRange("Alex", "M", 1112, 1113);

    assertEquals(expectedRankings, actualRankings);
  }

  @Test
  void differenceInRank() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q8 = new BabyNamesAnalysis(testReading);

    int expectedDifference = -1;

    int actualDifference = q8.differenceInRank(1111, 1113, "Isabella", "F");

    assertEquals(expectedDifference, actualDifference);
  }

  @Test
  void mostVolatileName() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q9 = new BabyNamesAnalysis(testReading);

    String expectedMostVolatileName = "Yasser";
    String actualMostVolatileName = q9.mostVolatileName(1111, 1113, "M");

    assertEquals(expectedMostVolatileName, actualMostVolatileName);
  }

  @Test
  void averageRankForName() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q10 = new BabyNamesAnalysis(testReading);

    double expectedAverageRank = 4.5;
    double actualAverageRank = q10.averageRankForName("Yasser", "M", 1111, 1114);

    assertEquals(expectedAverageRank, actualAverageRank);
  }

  @Test
  void highestAverageRank() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q11 = new BabyNamesAnalysis(testReading);

    String expectedHighestAverageRankedName = "Aohn";
    String actualHighestAverageRankedName = q11.highestAverageRank(1115, 1117, "M");

    assertEquals(expectedHighestAverageRankedName, actualHighestAverageRankedName);
  }

  @Test
  void recentAverageRank() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q12 = new BabyNamesAnalysis(testReading);

    double expectedRecentAverageRank = 5.25;
    double actualRecentAverageRank = q12.recentAverageRank("Yasser", "M", 4);

    assertEquals(expectedRecentAverageRank, actualRecentAverageRank);

  }

  @Test
  void namesAtRankInRange() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q13 = new BabyNamesAnalysis(testReading);

    List<String> expectedNamesAtRankInRange = List
        .of("Aohn", "Alex", "Alex", "Alex", "Alex", "Aohn", "Flex");
    List<String> actualNamesAtRankInRange = q13.namesAtRankInRange(1111, 1117, "M", 2);

    assertEquals(expectedNamesAtRankInRange, actualNamesAtRankInRange);

  }

  @Test
  void namesMostOftenAtRank() throws Exception {
    DataReader testReading = new DataReader("read_test", false);
    BabyNamesAnalysis q14 = new BabyNamesAnalysis(testReading);

    Map<String, Double> expectedNamesMostOftenAtRank = new HashMap<>();
    expectedNamesMostOftenAtRank.put("Alex", 4.0);
    Map<String, Double> actualNamesMostOftenAtRank = q14.namesMostOftenAtRank(1111, 1116, "M", 2);

    assertEquals(expectedNamesMostOftenAtRank, actualNamesMostOftenAtRank);
  }


}