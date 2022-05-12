import sunrise.RunSunRise;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        Runnable game = new RunSunRise();
        SwingUtilities.invokeLater(game);
    }
}
