package test.sunrise;

import org.junit.jupiter.api.Test;

import main.sunrise.gamedata.GameTimer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTimerTest {

    @Test
    public void testConvertNanoSecondsToSecondsPositiveInput() {
        int oneSecond = GameTimer.nanoToUnit(1000000000);
        assertEquals(1, oneSecond);
        int twoSeconds = GameTimer.nanoToUnit(2000000000);
        assertEquals(2, twoSeconds);
    }

    @Test
    public void testConvertNanoSecondsToSecondsNegativeInput() {
        int zeroSecond1 = GameTimer.nanoToUnit(-1000000000);
        assertEquals(0, zeroSecond1);
        int zeroSeconds2 = GameTimer.nanoToUnit(-2000000000);
        assertEquals(0, zeroSeconds2);
    }

    @Test
    public void testGetHoursPositiveInput() {
        int oneHour = GameTimer.getHours(3600);
        assertEquals(1, oneHour);
        int tenHours = GameTimer.getHours(36000);
        assertEquals(10, tenHours);
    }

    @Test
    public void testGetHoursNegativeInput() {
        int zeroHours = GameTimer.getHours(-1000);
        assertEquals(0, zeroHours);
    }

    @Test
    public void testGetMinutesPositiveInput() {
        int oneMinute = GameTimer.getMinutes(60);
        assertEquals(1, oneMinute);
        int threeMinutes = GameTimer.getMinutes(180);
        assertEquals(3, threeMinutes);
    }

    @Test
    public void testGetMinutesNegativeInput() {
        int zeroMinutes = GameTimer.getMinutes(-1000);
        assertEquals(0, zeroMinutes);
    }

    @Test
    public void testGetSecondsPositiveInput() {
        int oneSecond = GameTimer.getSeconds(1);
        assertEquals(1, oneSecond);
        int fortySeconds = GameTimer.getSeconds(40);
        assertEquals(40, fortySeconds);
    }

    @Test
    public void testGetSecondsNegativeInput() {
        int zeroSeconds = GameTimer.getSeconds(-50);
        assertEquals(0, zeroSeconds);
    }

    @Test
    public void testGetHoursMinutesAndSeconds() {
        int timeInSeconds = 3798;
        assertEquals(1, GameTimer.getHours(timeInSeconds));
        assertEquals(3, GameTimer.getMinutes(timeInSeconds));
        assertEquals(18, GameTimer.getSeconds(timeInSeconds));
    }
}
