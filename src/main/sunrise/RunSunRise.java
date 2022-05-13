package src.main.sunrise;

import src.main.sunrise.mechanics.GameField;
import src.main.sunrise.misc.Constants;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;

public class RunSunRise implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("SUN RISE!!!");
        frame.setLocation(Constants.WINDOW_LOCATION_X, Constants.WINDOW_LOCATION_Y);

        final JPanel points_panel = new JPanel();
        frame.add(points_panel, BorderLayout.NORTH);
        final JLabel pointLabel = new JLabel("Points: " + 0);
        points_panel.add(pointLabel);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel statusLabel = new JLabel("Running...");
        status_panel.add(statusLabel);

        final GameField field = new GameField(statusLabel, pointLabel);
        frame.add(field, BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
