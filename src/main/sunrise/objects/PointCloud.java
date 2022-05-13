package src.main.sunrise.objects;

import src.main.sunrise.misc.Constants;
import src.main.sunrise.misc.Direction;
import src.main.sunrise.mechanics.GameField;
import src.main.sunrise.gamedata.Scores;

import java.awt.Color;

public class PointCloud extends Cloud {
    public PointCloud(int x, int y, int width, Direction d) {
        super(x, y, width, Constants.CLOUD_SPECIAL_HEIGHT, Color.PINK, d);
    }

    @Override
    public void ability(GameField gameField) {
        if (detectCollision(gameField.getSun())) {
            Scores scoreObj = gameField.getScoreObj();
            scoreObj.setScores(scoreObj.getScore() + 1);
        }
    }
}
