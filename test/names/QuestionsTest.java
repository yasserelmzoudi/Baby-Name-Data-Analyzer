package names;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsTest {

    @Test
    void topRanked_standard() throws Exception {
        DataReader testReading = new DataReader("read_test");
        Questions topRanked = new Questions(testReading);

        String expectedRank = "Top Ranked 2000 Female Name: Emily\n" +
                              "Top Ranked 2000 Male Name: Jacob";
        String actualRank = topRanked.topRanked(2006);
        assertEquals(expectedRank, actualRank);
    }
}