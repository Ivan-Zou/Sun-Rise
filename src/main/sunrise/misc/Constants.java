package main.sunrise.misc;

public class Constants {
    // Game Window/Field constants
    public static final int WINDOW_LOCATION_X = 445;
    public static final int WINDOW_LOCATION_Y = 50;
    public static final int FIELD_WIDTH = 500;
    public static final int FIELD_HEIGHT = 500;
    public static final int REFRESH_RATE = 31;

    // Sun constants
    public static final int SUN_RADIUS = 30;
    public static final int SUN_STARTING_X = 250;
    public static final int SUN_STARTING_Y = 400;
    public static final int SUN_SPEED = 1;

    // Cloud constants
    public static final int CLOUD_TIME_INTERVAL = 50;
    public static final int CLOUD_HEIGHT = 60;
    public static final int CLOUD_SPECIAL_HEIGHT = 30;
    public static final int CLOUD_SPEED = 7;
    public static final int CLOUD_GAP = 220;
    public static final int CLOUD_STARTING_Y = -70;

    // Probabilities from 0 to 100
    public static final int PROBABILITY_OF_SHRINK_SUN_CLOUD = 5;
    public static final int PROBABILITY_OF_POINT_CLOUD = 30;

    // Score positions on screen
    public static final int SCORE_POSITION_X = 350;
    public static final int SCORE_POSITION_Y = 295;
    public static final int HIGH_SCORE_POSITION_X = 350;
    public static final int HIGH_SCORE_POSITION_Y = 215;

    // Image File Addresses
    public static final String START_SCREEN_FILE = "files/startscreen.png";
    public static final String INSTRUCTION_SCREEN_FILE = "files/instructionscreen.png";
    public static final String INSTRUCTION_SCREEN_2_FILE = "files/instructionscreen2.png";
    public static final String INSTRUCTION_SCREEN_3_FILE = "files/instructionscreen3.png";
    public static final String SKY_BACKGROUND_FILE = "files/skybackground.png";
    public static final String GAME_OVER_SCREEN_FILE = "files/gameoverscreen.png";
    public static final String ERROR_SCREEN_FILE = "files/errorscreen.png";
    public static final String SUN_FILE = "files/sun.png";

    // Text File Addresses
    public static final String HIGH_SCORE_FILE = "files/highscore.txt";
    public static final String SCORES_FILE = "files/scores.txt";
    public static final String GAME_TIME_FILE = "files/gametime.txt";
}
