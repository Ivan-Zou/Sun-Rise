package main;

import main.sunrise.RunSunRise;

import javax.swing.SwingUtilities;

public class Game {
    public static void main(String[] args) {
        Runnable game = new RunSunRise();
        SwingUtilities.invokeLater(game);
    }
}
