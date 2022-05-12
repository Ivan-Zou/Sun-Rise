package sunrise;

import java.awt.*;

public abstract class Cloud {
    private final int x;
    private int y;
    private final int width;
    private final int height;

    private final Direction direction;
    private final Color color;

    public Cloud(int x, int y, int width, int height, Color color, Direction d) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.color = color;
        this.direction = d;
        this.height = height;
    }

    public void move() {
        this.y += Constants.CLOUD_SPEED;
    }

    public boolean detectCollision(Sun sun) {
        return ((sun.getX() + sun.getRadius() >= this.x &&
                        this.direction == Direction.RIGHT_CLOUD) ||
                (sun.getX() - sun.getRadius() <= this.width &&
                        this.direction == Direction.LEFT_CLOUD)) &&
                (sun.getY() + sun.getRadius() >= this.y) &&
                        (sun.getY() - sun.getRadius() <= (this.y + this.height));
    }

    public boolean detectPassing(Sun sun) {
        return (sun.getY() - sun.getRadius() >= this.y + 20) &&
                (sun.getY() - sun.getRadius() <= this.y + 30) &&
                ((sun.getX() - sun.getRadius() >= this.width &&
                        this.direction == Direction.LEFT_CLOUD) ||
                (sun.getX() + sun.getRadius() <= this.x &&
                        this.direction == Direction.RIGHT_CLOUD));
    }

    public boolean isOffScreen() {
        return (this.y > Constants.FIELD_HEIGHT);
    }

    public void addPassingPoint(GameField gameField) {
        Scores scoreObj = gameField.getScoreObj();
        if (detectPassing(gameField.getSun())) {
            scoreObj.setScores(scoreObj.getScore() + 1);
        }
    }

    public abstract void ability(GameField gameField);

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}
