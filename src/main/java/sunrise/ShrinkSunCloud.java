package sunrise;

import java.awt.*;

public class ShrinkSunCloud extends Cloud {
    public ShrinkSunCloud(int x, int y, int width, Direction d) {
        super(x, y, width, Constants.CLOUD_SPECIAL_HEIGHT, Color.ORANGE, d);
    }

    @Override
    public void ability(GameField gameField) {
        Sun sun = gameField.getSun();
        if (detectCollision(sun)) {
            if (sun.getRadius() > 20) {
                sun.setRadius(sun.getRadius() - 1);
            }
        }
    }
}
