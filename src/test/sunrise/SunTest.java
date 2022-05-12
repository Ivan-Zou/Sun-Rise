package src.test.sunrise;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import src.main.sunrise.mechanics.GameField;
import src.main.sunrise.misc.*;
import src.main.sunrise.objects.*;

public class SunTest {

    @Test
    public void testSunGetterMethods() {
        Sun sun = new Sun(5, 5, 10, Direction.RIGHT_SUN);
        assertEquals(5, sun.getX());
        assertEquals(5, sun.getY());
        assertEquals(10, sun.getRadius());
    }

    @Test
    public void testSunRadiusSetterMethod() {
        Sun sun = new Sun(5, 5, 25, Direction.RIGHT_SUN);
        sun.setRadius(50);
        assertEquals(50, sun.getRadius());
    }

    @Test
    public void testSunMovement() {
        Sun sun = new Sun(10, 10, 10, Direction.RIGHT_SUN);
        assertEquals(10, sun.getX());
        assertEquals(10, sun.getY());
        sun.move(); // move then stop makes the sun move 1 pixel, since the sun accelerates
        sun.stop();
        assertEquals(11, sun.getX());
        assertEquals(10, sun.getY()); // because the sun only moves horizontally in game
        sun.changeDirection();
        sun.move();
        sun.stop();
        assertEquals(10, sun.getX());
        assertEquals(10, sun.getY());
        sun.move();
        sun.stop();
        assertEquals(9, sun.getX());
        assertEquals(10, sun.getY());
    }

    @Test
    public void testSunScreenEdgeCollision() {
        Sun sun = new Sun(20, 20, 1, Direction.LEFT_SUN);
        GameField gameField = new GameField(sun);
        for (int i = 0; i < 25; i++) {
            //move 20 pixels to the left
            sun.move();
            sun.stop();
        }
        assertTrue(sun.detectScreenEdgeCollision());
        sun.changeDirection();
        for (int i = 0; i < 255; i++) {
            // move 255 pixels to the right
            sun.move();
            sun.stop();
        }
        assertFalse(sun.detectScreenEdgeCollision());
        for (int i = 0; i < 250; i++) {
            // move 250 pixels to the right
            sun.move();
            sun.stop();
        }
        assertTrue(sun.detectScreenEdgeCollision());
        sun.screenEdgeCollision(gameField);
        Assertions.assertEquals(Screens.GAME_OVER, gameField.getScreen());
    }
}
