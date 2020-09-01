package names;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {

    @Test
    void topRanked_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q1 = new Questions(testReading);

        String expectedRank = "Isabella Alex";
        String actualRank = q1.topRanked(1111);
        assertEquals(expectedRank, actualRank);
    }

    @Test
    void nameBabyCount() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q2 = new Questions(testReading);

        String expectedNameBabyCount = "2 70";
        String actualNameBabyCount = q2.nameBabyCount(1111, "M", 'A');
        assertEquals(expectedNameBabyCount, actualNameBabyCount);
    }

    @Test
    void allRankings() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q3 = new Questions(testReading);

        Map<Integer, Integer> expectedRankings = new HashMap<>();
        expectedRankings.put(1111, 1);
        expectedRankings.put(1112, 2);

        Map<Integer, Integer> actualRankings = q3.allRankings("Alex", "M");
        assertEquals(expectedRankings, actualRankings);
    }

    @Test
    void todayName_male_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q4 = new Questions(testReading);

        String expectedTodayName = "Alex";
        String actualTodayName = q4.todayName("Aohn", "M", 1111);
        assertEquals(expectedTodayName, actualTodayName);
    }

    @Test
    void todayName_female_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q4 = new Questions(testReading);

        String expectedTodayName = "Imma";
        String actualTodayName = q4.todayName("Isabella", "F", 1111);
        assertEquals(expectedTodayName, actualTodayName);
    }
}