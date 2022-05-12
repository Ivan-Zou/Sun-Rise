package sunrise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;

public class GameTimer {
    private int startTime;
    private int endTime;

    public GameTimer() {
        startTime = 0;
        endTime = 0;
    }

    public void start() {
        startTime = convertNanoSecondsToSeconds(System.nanoTime());
    }

    public void end() {
        endTime = convertNanoSecondsToSeconds(System.nanoTime());
    }

    public static int convertNanoSecondsToSeconds(long nano) {
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

    public int getTimeElapsed() {
        return endTime - startTime;
    }

    public void writeGameTimeToFile(LinkedList<Integer> times) throws IOException {
        File file = Paths.get(Constants.GAME_TIME_FILE).toFile();
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter writer = new BufferedWriter(fileWriter);
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

    public static int getHours(int timeInSeconds) {
        if (timeInSeconds >= 0) {
            return (timeInSeconds / 3600);
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

    public static int getSeconds(int timeInSeconds) {
        if (timeInSeconds >= 0) {
            return (timeInSeconds -
                    (getMinutes(timeInSeconds) * 60) -
                    (getHours(timeInSeconds) * 3600));
        } else {
            return 0;
        }
    }
}
