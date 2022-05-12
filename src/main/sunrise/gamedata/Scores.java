package src.main.sunrise.gamedata;

import src.main.sunrise.misc.Constants;

import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Scores {
    private int highScore;
    private int score;

    public Scores() {
        try {
            score = 0;
            setHighScoreFromFile();
        } catch (IOException exception) {
            highScore = 0;
        }
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setScores(int score) {
        if (score >= 0) {
            this.score = score;
            if (highScore < score) {
                highScore = score;
            }
        } else {
            this.score = 0;
        }
    }

    public void writeHighScoreToFile() throws IOException {
        String stringOfHighScore = "High Score: " + highScore;
        File file = Paths.get(Constants.HIGH_SCORE_FILE).toFile();
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(stringOfHighScore);
        writer.flush();
        writer.close();
    }

    public int getHighScoreFromString(String line) {
        int highscr;
        try {
            highscr = Integer.parseInt(line.substring(12));
        } catch (IndexOutOfBoundsException | NullPointerException | NumberFormatException
                exception) {
            highscr = 0;
        }
        return highscr;
    }

    public void setHighScoreFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Constants.HIGH_SCORE_FILE));
        String line = reader.readLine();
        highScore = getHighScoreFromString(line);
    }

    public void writeScoresToFile(LinkedList<Integer> scores) throws IOException {
        File file = Paths.get(Constants.SCORES_FILE).toFile();
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        int gameNumber = 0;
        writer.write("Scores:");
        writer.newLine();
        for (int score: scores) {
            gameNumber++;
            writer.write("Game " + gameNumber + ": " + score);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}