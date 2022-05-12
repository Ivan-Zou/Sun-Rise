package src.main;

import src.main.sunrise.RunSunRise;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Runnable game = new RunSunRise();
        SwingUtilities.invokeLater(game);
    }
}
