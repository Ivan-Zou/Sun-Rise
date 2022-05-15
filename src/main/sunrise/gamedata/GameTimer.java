package main.sunrise.gamedata;

import main.sunrise.misc.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;

public class GameTimer {
    private final LinkedList<Integer> times;
    private int startTime, endTime;

    public GameTimer() {
        startTime = 0;
        endTime = 0;
        times = new LinkedList<>();
    }

    public void start() {
        startTime = nanoToUnit(System.nanoTime());
    }

    public void end() {
        endTime = nanoToUnit(System.nanoTime());
    }

    public static int nanoToUnit(long nano) {
        if (nano >= 0) {
            return (int) (nano / Math.pow(10,9));
        } else {
            return 0;
        }
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
    }

    public void recordTime() {
        times.add(getTimeElapsed());
    }

    public int getTimeElapsed() {
        return endTime - startTime;
    }

    public void writeGameTimeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter
                (new FileWriter(Constants.GAME_TIME_FILE, false));
        int gameNumber = 0;
        writer.write("Game Times:");
        writer.newLine();
        for (int time: times) {
            gameNumber++;
            writer.write("Game " + gameNumber + ": " +  getHours(time) + " hour(s) "
                    + getMinutes(time) + " minute(s) "
                    + getSeconds(time) + " second(s)");
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public static int getSeconds(int timeInSeconds) {
        if (timeInSeconds >= 0) {
            return (timeInSeconds -
                    (getMinutes(timeInSeconds) * 60) -
                    (getHours(timeInSeconds) * 3600));
        } else {
            return 0;
        }
    }

    public static int getMinutes(int timeInSeconds) {
        if (timeInSeconds >= 0) {
            return ((timeInSeconds / 60) - getHours(timeInSeconds) * 60);
        } else {
            return 0;
        }
    }

    public static int getHours(int timeInSeconds) {
        if (timeInSeconds >= 0) {
            return (timeInSeconds / 3600);
        } else {
            return 0;
        }
    }
}
