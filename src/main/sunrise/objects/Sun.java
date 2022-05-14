package main.sunrise.objects;

import main.sunrise.mechanics.GameField;
import main.sunrise.misc.Constants;
import main.sunrise.misc.Direction;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics;

public class Sun {
    private Image sunImage;
    private final ImageIcon sunIcon;

    private int x;
    private int y;
    private int radius;
    private int vx;

    private Direction direction;

    public Sun(int x, int y, int radius, Direction direct) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.direction = direct;

        this.vx = 0;

        sunIcon = new ImageIcon(Constants.SUN_FILE);
        sunImage = sunIcon.getImage().getScaledInstance(
                2 * this.radius, 2 * this.radius, Image.SCALE_SMOOTH);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        sunImage = sunIcon.getImage().getScaledInstance(
                2 * this.radius, 2 * this.radius, Image.SCALE_SMOOTH);
    }

    public void resetPosition() {
        this.x = Constants.SUN_STARTING_X;
        this.y = Constants.SUN_STARTING_Y;
    }

    public void stop() {
        this.vx = 0;
    }

    public void move() {
        if (direction == Direction.LEFT_SUN) {
            this.vx -= Constants.SUN_SPEED;
        } else if (direction == Direction.RIGHT_SUN) {
            this.vx += Constants.SUN_SPEED;
        }
        this.x += this.vx;
    }

    public void screenEdgeCollision(GameField gameField) {
        if (detectScreenEdgeCollision()) {
            gameField.gameOver();
        }
    }

    public boolean detectScreenEdgeCollision() {
        return this.x - this.radius < 0 || this.x + this.radius > Constants.FIELD_WIDTH;
    }

    public void changeDirection() {
        this.direction = (direction == Direction.LEFT_SUN) ? Direction.RIGHT_SUN :
                Direction.LEFT_SUN;
    }

    public void draw(Graphics g) {
        g.drawImage(sunImage, this.x - this.radius, this.y - this.radius, null);
    }
}
