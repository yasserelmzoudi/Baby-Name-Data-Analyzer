package names;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {

    @Test
    void topRanked_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q1 = new Questions(testReading);

        String expectedRank = "Top Ranked 1111 Female Name: Isabella\n" +
                              "Top Ranked 1111 Male Name: Alex\n";
        String actualRank = q1.topRanked(1111);
        assertEquals(expectedRank, actualRank);
    }

    @Test
    void nameBabyCount() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q2 = new Questions(testReading);

        String expectedNameBabyCount = "Total 1111 M names starting with 'A': 2\n" +
                                       "Total 1111 M babies whose name Starts with 'A': 70\n";
        String actualNameBabyCount = q2.nameBabyCount(1111, "M", 'A');
        assertEquals(expectedNameBabyCount, actualNameBabyCount);
    }

    @Test
    void allRankings() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions q3 = new Questions(testReading);

        String expectedRankings = "1111 Alex Ranking: 1\n" +
                                  "1112 Alex Ranking: 2\n";
        String actualRankings = q3.allRankings("Alex", "M");
        assertEquals(expectedRankings, actualRankings);
    }
}