package sunrise;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScoresTest {
    @Test
    public void testGetHighScoreFromString() {
        Scores scores = new Scores();
        assertEquals(0, scores.getHighScoreFromString(""));
        assertEquals(0, scores.getHighScoreFromString(null));
        assertEquals(0, scores.getHighScoreFromString("High Score: (*&#"));
        assertEquals(55, scores.getHighScoreFromString("High Score: 55"));
        assertEquals(111, scores.getHighScoreFromString("High Score: 111"));
    }

    @Test
    public void testGetterAndSetterForScore() {
        Scores scores = new Scores();
        scores.setScores(22);
        assertEquals(22, scores.getScore());
        scores.setScores(-100);
        assertEquals(0, scores.getScore());
    }
}
