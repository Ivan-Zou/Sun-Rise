package src.main.sunrise.objects;

import src.main.sunrise.misc.Constants;
import src.main.sunrise.misc.Direction;
import src.main.sunrise.mechanics.GameField;

import java.awt.*;

public class NormalCloud extends Cloud {
    public NormalCloud(int x, int y, int width, Direction d) {
        super(x, y, width, Constants.CLOUD_HEIGHT, Color.WHITE, d);
    }

    @Override
    public void ability(GameField gameField) {
        if (detectCollision(gameField.getSun())) {
            gameField.gameOver();
        }
    }
}
