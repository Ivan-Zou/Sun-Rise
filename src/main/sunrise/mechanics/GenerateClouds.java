package main.sunrise.mechanics;

import main.sunrise.objects.NormalCloud;
import main.sunrise.objects.PointCloud;
import main.sunrise.objects.ShrinkSunCloud;
import main.sunrise.misc.Constants;
import main.sunrise.misc.Direction;
import main.sunrise.objects.Cloud;

public class GenerateClouds {
    private static int timer = 0;
    public static Cloud[] generateLeftAndRightCloud() {
        timer--;
        if (timer <= 0) {
            Cloud[] clouds = new Cloud[2];
            int cloudProbability = generateRandomInt(0, 100);
            int leftWidth = generateRandomInt(0, (int) (Constants.FIELD_WIDTH / 2.25));
            int rightX = leftWidth + Constants.CLOUD_GAP;
            int rightWidth = Constants.FIELD_WIDTH - rightX;
            int y = Constants.CLOUD_STARTING_Y;
            if (cloudProbability <= Constants.PROBABILITY_OF_SHRINK_SUN_CLOUD) {
                clouds[0] = new ShrinkSunCloud(0, y, leftWidth, Direction.LEFT_CLOUD);
                clouds[1] = new ShrinkSunCloud(rightX, y, rightWidth, Direction.RIGHT_CLOUD);
            } else if (cloudProbability <= Constants.PROBABILITY_OF_POINT_CLOUD) {
                clouds[0] = new PointCloud(0, y, leftWidth, Direction.LEFT_CLOUD);
                clouds[1] = new PointCloud(rightX, y, rightWidth, Direction.RIGHT_CLOUD);
            } else {
                clouds[0] = new NormalCloud(0, y, leftWidth, Direction.LEFT_CLOUD);
                clouds[1] = new NormalCloud(rightX, y, rightWidth, Direction.RIGHT_CLOUD);
            }
            timer = Constants.CLOUD_TIME_INTERVAL;
            return clouds;
        } else {
            return null;
        }
    }

    public static int generateRandomInt(int min, int max) {
        int start = min;
        int end = max;

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        if (end < start) {
            int temp = start;
            start = end;
            end = temp;
        }

        return (int) ((Math.random() * (end - start)) + start);
    }
}
