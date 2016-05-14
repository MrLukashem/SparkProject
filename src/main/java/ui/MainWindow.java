package ui;

import javax.swing.*;

/**
 * Created by mrlukashem on 14.05.16.
 */
public class MainWindow extends JFrame {
    private JPanel mMainPanel;

    public MainWindow() {
        super();

        setContentPane(mMainPanel);
    }

    public void showMe() {
        setVisible(true);
    }
}
