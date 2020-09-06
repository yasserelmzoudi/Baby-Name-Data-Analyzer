package names;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BabyNamesAnalysisTest {

    @Test
    void topRanked_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q1 = new BabyNamesAnalysis(testReading);

        String[] expectedRank = {"Isabella", "Alex"};
        String[] actualRank = q1.topRanked(1111);
        assertArrayEquals(expectedRank, actualRank);
    }

    @Test
    void nameBabyCount() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q2 = new BabyNamesAnalysis(testReading);

        int[] expectedNameBabyCount = {2, 70};
        int[] actualNameBabyCount = q2.nameBabyCount(1111, "M", 'A');
        assertArrayEquals(expectedNameBabyCount, actualNameBabyCount);
    }

    @Test
    void allRankings() throws Exception {
        DataReader testReading = new DataReader("read_test");
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
    void todayName_male_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q4 = new BabyNamesAnalysis(testReading);

        String expectedTodayName = "Flex";
        String actualTodayName = q4.todayName("Aohn", "M", 1111);
        assertEquals(expectedTodayName, actualTodayName);
    }

    @Test
    void todayName_female_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q4 = new BabyNamesAnalysis(testReading);

        String expectedTodayName = "Imma";
        String actualTodayName = q4.todayName("Isabella", "F", 1111);
        assertEquals(expectedTodayName, actualTodayName);
    }

    @Test
    void mostPopularName() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q5 = new BabyNamesAnalysis(testReading);

        List<String> expectedTodayName = List.of("Imma", "3");
        List<String> actualTodayName = q5.mostPopularName(1111, 1114, "F");
        assertEquals(expectedTodayName, actualTodayName);
    }

    @Test
    void mostPopularLetter() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q6 = new BabyNamesAnalysis(testReading);

        List<String> expectedMostPopularLetter = List.of("Imma", "Isabella");
        List<String> actualMostPopularLetter = q6.mostPopularLetter(1111, 1114);
        assertEquals(expectedMostPopularLetter, actualMostPopularLetter);
    }

    @Test
    void allRankingsRange() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q7 = new BabyNamesAnalysis(testReading);

        Map<Integer, Integer> expectedRankings = new HashMap<>();
        expectedRankings.put(1112, 2);
        expectedRankings.put(1113, 2);

        Map<Integer, Integer> actualRankings = q7.allRankingsRange("Alex", "M", 1112, 1113);
        assertEquals(expectedRankings, actualRankings);
    }

    @Test
    void differenceInRank() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q8 = new BabyNamesAnalysis(testReading);

        int expectedDifference = -1;

        int actualDifference = q8.differenceInRank(1111, 1113, "Isabella", "F");

        assertEquals(expectedDifference, actualDifference);
    }

    @Test
    void mostVolatileName() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q9 = new BabyNamesAnalysis(testReading);

        String expectedMostVolatileName = "Yasser";
        String actualMostVolatileName = q9.mostVolatileName(1111, 1113, "M");

        assertEquals(expectedMostVolatileName, actualMostVolatileName);
    }

    @Test
    void averageRankForName() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q10 = new BabyNamesAnalysis(testReading);

        double expectedAverageRank = 4.5;
        double actualAverageRank = q10.averageRankForName("Yasser", "M", 1111, 1114);

        assertEquals(expectedAverageRank, actualAverageRank);
    }

    @Test
    void highestAverageRank() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q11 = new BabyNamesAnalysis(testReading);

        String expectedHighestAverageRankedName = "Aohn";
        String actualHighestAverageRankedName = q11.highestAverageRank(1115, 1117, "M");

        assertEquals(expectedHighestAverageRankedName, actualHighestAverageRankedName);
    }

    @Test
    void recentAverageRank() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q12 = new BabyNamesAnalysis(testReading);

        double expectedRecentAverageRank = 5.25;
        double actualRecentAverageRank = q12.recentAverageRank("Yasser", "M", 4);

        assertEquals(expectedRecentAverageRank, actualRecentAverageRank);

    }

    @Test
    void namesAtRankInRange() throws Exception {
        DataReader testReading = new DataReader("read_test");
        BabyNamesAnalysis q13 = new BabyNamesAnalysis(testReading);

        List<String> expectedNamesAtRankInRange = List.of("Aohn", "Alex", "Alex", "Alex", "Alex", "Aohn", "Flex");
        List<String> actualNamesAtRankInRange = q13.namesAtRankInRange(1111, 1117, "M", 2);

        assertEquals(expectedNamesAtRankInRange, actualNamesAtRankInRange);

    }




}