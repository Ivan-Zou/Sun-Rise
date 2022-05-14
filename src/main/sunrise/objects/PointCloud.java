package main.sunrise.objects;

import main.sunrise.misc.Constants;
import main.sunrise.misc.Direction;
import main.sunrise.mechanics.GameField;
import main.sunrise.gamedata.Scores;

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
