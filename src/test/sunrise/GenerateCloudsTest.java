package test.sunrise;

import org.junit.jupiter.api.Test;

import main.sunrise.mechanics.GenerateClouds;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateCloudsTest {

    @Test
    public void testGenerateRandomIntWithIntsInBound() {
        int random = GenerateClouds.generateRandomInt(0, 2);
        assertTrue(random >= 0);
        assertTrue(random <= 2);
    }

    @Test
    public void testGenerateRandomIntWithTwoNegativeInts() {
        int random = GenerateClouds.generateRandomInt(-20, -10);
        assertEquals(0, random);
        assertFalse(random > 0);
        assertFalse(random < 0);
    }

    @Test
    public void testGenerateRandomIntWithOneNegativeInt() {
        int random = GenerateClouds.generateRandomInt(-15, 3);
        assertTrue(random >= 0);
        assertTrue(random <= 3);
    }

    @Test
    public void testGenerateRandomIntWithFlippedMinAndMax() {
        int random = GenerateClouds.generateRandomInt(4, 1);
        assertTrue(random <= 4);
        assertTrue(random >= 1);
    }


}
