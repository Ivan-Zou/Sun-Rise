package main.sunrise.mechanics;

import main.sunrise.misc.Constants;
import main.sunrise.misc.Direction;
import main.sunrise.misc.Screens;
import main.sunrise.gamedata.GameTimer;
import main.sunrise.gamedata.Scores;
import main.sunrise.objects.Cloud;
import main.sunrise.objects.Sun;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.BorderFactory;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.io.IOException;

import java.util.Arrays;
import java.util.LinkedList;

public class GameField extends JPanel {
    private final Scores scores;
    private final GameTimer gameTimer;

    private final Sun sun;

    private Image startScreenImage;
    private Image instructionScreenImage, instructionScreen2Image, instructionScreen3Image;
    private Image skyImage;
    private Image gameOverScreenImage;
    private Image errorScreenImage;

    private Screens screen;
    private JLabel status, points;

    private LinkedList<Cloud> clouds;

    private boolean fileIsWritten;

    // For Testing Purposes
    public GameField(Sun sun) {
        scores = new Scores();
        gameTimer = new GameTimer();
        this.sun = sun;
        screen = Screens.START;
    }

    public GameField(JLabel status, JLabel points) {
        scores = new Scores();
        gameTimer = new GameTimer();

        sun = new Sun(Constants.SUN_STARTING_X, Constants.SUN_STARTING_Y,
                Constants.SUN_RADIUS, Direction.LEFT_SUN);

        screen = Screens.START;

        this.status = status;
        this.points = points;

        clouds = new LinkedList<>();

        fileIsWritten = false;

        ImageIcon skyBackgroundIcon = new ImageIcon(Constants.SKY_BACKGROUND_FILE);
        skyImage = skyBackgroundIcon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);
        ImageIcon startScreenIcon = new ImageIcon(Constants.START_SCREEN_FILE);
        startScreenImage = startScreenIcon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);
        ImageIcon instructionScreenIcon = new ImageIcon(Constants.INSTRUCTION_SCREEN_FILE);
        instructionScreenImage = instructionScreenIcon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);
        ImageIcon instructionScreen2Icon = new ImageIcon(Constants.INSTRUCTION_SCREEN_2_FILE);
        instructionScreen2Image = instructionScreen2Icon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);
        ImageIcon instructionScreen3Icon = new ImageIcon(Constants.INSTRUCTION_SCREEN_3_FILE);
        instructionScreen3Image = instructionScreen3Icon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);
        ImageIcon gameOverScreenIcon = new ImageIcon(Constants.GAME_OVER_SCREEN_FILE);
        gameOverScreenImage = gameOverScreenIcon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);
        ImageIcon errorScreenIcon = new ImageIcon(Constants.ERROR_SCREEN_FILE);
        errorScreenImage = errorScreenIcon.getImage().getScaledInstance(
                Constants.FIELD_WIDTH,Constants.FIELD_HEIGHT,Image.SCALE_SMOOTH);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Timer timer = new Timer(Constants.REFRESH_RATE, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tick();
                } catch (IOException exception) {
                    status.setText("FATAL ERROR!!!");
                    screen = Screens.ERROR;
                }
            }
        });

        timer.start();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (screen == Screens.START) {
                        screen = Screens.INSTRUCTION_1;
                        repaint();
                    } else if (screen == Screens.INSTRUCTION_1) {
                        screen = Screens.INSTRUCTION_2;
                        repaint();
                    } else if (screen == Screens.INSTRUCTION_2) {
                        screen = Screens.INSTRUCTION_3;
                        repaint();
                    } else if (screen == Screens.INSTRUCTION_3) {
                        screen = Screens.PLAYING;
                        gameTimer.start();
                        repaint();
                    } else if (screen == Screens.PLAYING) {
                        sun.changeDirection();
                    } else if (screen == Screens.GAME_OVER) {
                        reset();
                        repaint();
                    }
                }
            }
        });
    }

    void tick() throws IOException {
        if (screen == Screens.PLAYING) {
            sun.move();
            sun.screenEdgeCollision(this);

            Cloud[] newClouds = GenerateClouds.generateLeftAndRightCloud();
            if (newClouds != null) {
                clouds.addAll(Arrays.asList(newClouds));
            }
            repaint();

            for (Cloud cl : clouds) {
                if (cl != null) {
                    cl.move();
                    cl.addPassingPoint(this);
                    cl.ability(this);
                    repaint();
                }
            }
            clouds.removeIf(cl -> cl != null && cl.isOffScreen());
            repaint();
            points.setText("Points: " + scores.getScore());
            repaint();
        }

        if (screen == Screens.GAME_OVER) {
            if (!fileIsWritten) {
                writeGameDataToFiles();
                fileIsWritten = true;
            }
        }
        repaint();
    }

    public void writeGameDataToFiles() throws IOException {
        scores.recordScore();
        scores.writeToFile();
        gameTimer.end();
        gameTimer.recordTime();
        gameTimer.writeGameTimeToFile();
        status.setText("Game Over!");
    }

    public void reset()  {
        fileIsWritten = false;
        screen = Screens.INSTRUCTION_1;
        clouds.clear();
        gameTimer.reset();
        scores.reset();
        status.setText("Running...");
        points.setText("Points: " + scores.getScore());
        sun.stop();
        sun.resetPosition();
        sun.setRadius(Constants.SUN_RADIUS);
        requestFocusInWindow();
    }

    public void gameOver() {
        screen = Screens.GAME_OVER;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (screen == Screens.ERROR) {
            g.drawImage(errorScreenImage, 0, 0, null);
        }
        if (screen == Screens.START) {
            g.drawImage(startScreenImage,0, 0, null);
        }
        if (screen == Screens.INSTRUCTION_1) {
            g.drawImage(instructionScreenImage, 0, 0, null);
        }
        if (screen == Screens.INSTRUCTION_2) {
            g.drawImage(instructionScreen2Image, 0, 0, null);
        }
        if (screen == Screens.INSTRUCTION_3) {
            g.drawImage(instructionScreen3Image, 0, 0, null);
        }
        if (screen == Screens.PLAYING) {
            g.drawImage(skyImage, 0, 0,null);
            sun.draw(g);
            for (Cloud cl : clouds) {
                if (cl != null) {
                    cl.draw(g);
                }
            }
        }
        if (screen == Screens.GAME_OVER) {
            g.drawImage(gameOverScreenImage, 0, 0, null);
            g.setFont(new Font(null, Font.PLAIN, 45));
            g.drawString("" + scores.getScore(),
                    Constants.SCORE_POSITION_X, Constants.SCORE_POSITION_Y);
            g.drawString("" + scores.getHighScore(),
                    Constants.HIGH_SCORE_POSITION_X, Constants.HIGH_SCORE_POSITION_Y);
        }
    }

    public Sun getSun() {
        return sun;
    }

    public Scores getScoreObj() {
        return scores;
    }

    public Screens getScreen() {
        return screen;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.FIELD_WIDTH, Constants.FIELD_HEIGHT);
    }
}
