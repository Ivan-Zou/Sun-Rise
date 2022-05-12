package sunrise;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CloudTest {

    @Test
    public void testIsOffScreenAndMove() {
        // I used normal clouds to represent all the different clouds because they all extend
        // the parent Cloud, and they do not override isOffScreen
        Cloud normalCloud = new NormalCloud(0, 0, 100, Direction.LEFT_CLOUD);
        assertFalse(normalCloud.isOffScreen());
        for (int i = 0; i < 75; i++) {
            // moves cloud 75 times, which moves the cloud 75 * 7 pixels
            normalCloud.move();
        }
        assertTrue(normalCloud.isOffScreen());
    }

    // I drew pictures to help visualize the field
    @Test
    public void testSunAndCloudCollisionAndPassing() {
        // I used normal clouds to represent all the different clouds because they all extend
        // the parent Cloud, and they do not override detectCollision or detectPassing
        Cloud normalCloud = new NormalCloud(0,0, 100, Direction.LEFT_CLOUD);
        Sun sun = new Sun(50, 100,10, Direction.LEFT_SUN);
        assertFalse(normalCloud.detectCollision(sun));
        assertFalse(normalCloud.detectPassing(sun));
        sun.changeDirection();
        for (int i = 0; i < 70; i++) {
            // moves the sun 70 pixels to the right
            // move then stop moves the 1 pixel at a time
            sun.move();
            sun.stop();
        }
        for (int i = 0; i < 9; i++) {
            // move cloud 9*7 pixels down
            normalCloud.move();
        }
        assertFalse(normalCloud.detectCollision(sun));
        assertTrue(normalCloud.detectPassing(sun));
        sun.changeDirection();
        for (int i = 0; i < 10; i++) {
            // move sun with acceleration to the left
            sun.move();
        }
        assertTrue(normalCloud.detectCollision(sun));
        assertFalse(normalCloud.detectPassing(sun));
    }

    @Test
    public void testSunAndCloudAddPassingPoint() {
        // I used normal clouds to represent all the different clouds because they all extend
        // the parent Cloud, and they do not override addPassingPoint.
        Sun sun = new Sun(125, 100, 10, Direction.LEFT_SUN);
        GameField gameField = new GameField(sun);
        Cloud normalCloud = new NormalCloud(0, 0, 100, Direction.LEFT_CLOUD);
        for (int i = 0; i < 9; i++) {
            // move cloud 9*7 pixels down
            normalCloud.move();
        }
        assertTrue(normalCloud.detectPassing(sun));
        assertFalse(normalCloud.detectCollision(sun));
        normalCloud.addPassingPoint(gameField);
        assertEquals(1, gameField.getScoreObj().getScore());
        for (int i = 0; i < 14; i++) {
            //add 14 points
            normalCloud.addPassingPoint(gameField);
        }
        assertEquals(15, gameField.getScoreObj().getScore());
        assertNotEquals(Screens.GAME_OVER, gameField.getScreen());
    }

    @Test
    public void testPointCloudAbility() {
        Sun sun = new Sun(50, 100, 15, Direction.LEFT_SUN);
        GameField gameField = new GameField(sun);
        Cloud pointCloud = new PointCloud(0, 0, 100, Direction.LEFT_CLOUD);
        for (int i = 0; i < 9; i++) {
            // move cloud 9*7 pixels down
            pointCloud.move();
        }
        assertTrue(pointCloud.detectCollision(sun));
        pointCloud.ability(gameField);
        assertEquals(1, gameField.getScoreObj().getScore());
        pointCloud.ability(gameField);
        assertEquals(2, gameField.getScoreObj().getScore());
        for (int i = 0; i < 10; i++) {
            // runs ability 10 times
            pointCloud.ability(gameField);
        }
        assertEquals(12, gameField.getScoreObj().getScore());
        assertNotEquals(Screens.GAME_OVER, gameField.getScreen());
    }

    @Test
    public void testShrinkSunCloudAbility() {
        Sun sun = new Sun(75, 75, 30, Direction.RIGHT_SUN);
        GameField gameField = new GameField(sun);
        Cloud shrinkSunCloud = new ShrinkSunCloud(0, 0, 100, Direction.LEFT_CLOUD);
        for (int i = 0; i < 10; i++) {
            // move cloud 10*7 pixels down
            shrinkSunCloud.move();
        }
        assertTrue(shrinkSunCloud.detectCollision(sun));
        shrinkSunCloud.ability(gameField);
        assertEquals(29, sun.getRadius());
        for (int i = 0; i < 15; i++) {
            //runs ability 15 times
            shrinkSunCloud.ability(gameField);
        }
        // Test the cap on Sun radius in shrinkCloudAbility
        assertEquals(20, sun.getRadius());
        assertNotEquals(14, sun.getRadius());
        assertNotEquals(Screens.GAME_OVER, gameField.getScreen());
    }

    @Test
    public void testNormalCloudAbility() {
        Sun sun = new Sun(50, 50, 2, Direction.RIGHT_SUN);
        GameField gameField = new GameField(sun);
        Cloud normalCloud = new NormalCloud(0, 0, 100, Direction.LEFT_CLOUD);
        for (int i = 0; i < 7; i++) {
            // move cloud 7*7 pixels down
            normalCloud.move();
        }
        assertTrue(normalCloud.detectCollision(sun));
        normalCloud.ability(gameField);
        assertEquals(Screens.GAME_OVER, gameField.getScreen());
    }
}
