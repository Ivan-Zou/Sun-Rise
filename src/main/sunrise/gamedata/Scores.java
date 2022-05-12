package src.main.sunrise.gamedata;

import src.main.sunrise.misc.Constants;

import java.io.*;
import java.util.LinkedList;

public class Scores {
    private int highScore, score;
    private final LinkedList<Integer> allScores;

    public Scores() {
        score = 0;
        setHighScoreFromFile();
        allScores = new LinkedList<>();
    }

    public void setHighScoreFromFile() {
        try {
            BufferedReader reader = new BufferedReader
                    (new FileReader(Constants.HIGH_SCORE_FILE));
            String line = reader.readLine();
            highScore = getHighScoreFromString(line);
            reader.close();
        } catch (IOException exception) {
            // File Does Not Exist
            highScore = 0;
        }

    }

    public int getHighScoreFromString(String line) {
        int high;
        try {
            high = Integer.parseInt(line.substring(12));
        } catch (IndexOutOfBoundsException |
                NullPointerException |
                NumberFormatException exception) {
            high = 0;
        }
        return high;
    }

    public int getScore() {
        return score;
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

    public void reset() {
        score = 0;
    }

    public int getHighScore() {
        return highScore;
    }

    public LinkedList<Integer> getAllRecordedScores() {
        return allScores;
    }

    public void recordScore() {
        allScores.add(score);
    }

    public void writeToFile() {
        writeScoresToFile();
        writeHighScoreToFile();
    }

    private void writeScoresToFile() {
        try {
            BufferedWriter writer = new BufferedWriter
                    (new FileWriter(Constants.SCORES_FILE, false));
            int gameNumber = 0;
            writer.write("Scores:");
            writer.newLine();
            for (int score: allScores) {
                gameNumber++;
                writer.write("game " + gameNumber + ": " + score);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void writeHighScoreToFile() {
        try {
            String stringOfHighScore = "High Score: " + highScore;
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(Constants.HIGH_SCORE_FILE, false));
            writer.write(stringOfHighScore);
            writer.flush();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}