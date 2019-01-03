package io.lerk.soultraps.sys.window;

import nl.paulinternet.gtasaveedit.view.swing.YBox;

import javax.swing.*;
import java.awt.*;

/**
 * This is a simple about window.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class AboutWindow extends JFrame {

    /**
     * The main content of this window.
     */
    private YBox yBox;

    /**
     * Constructor.
     */
    public AboutWindow() {

        JLabel label = new JLabel(
                "<html>" +
                        "<font size=+2>Soultraps</font><br />" +
                        "<font size=+1>2d RPG made with Greenfoot</font><br />" +
                        "<br />" +
                        "<br />Controls:<br />" +
                        "<br />W/A/S/D: Move<br />" +
                        "<br />E: Start conversation<br />" +
                        "<br />SPACE: Dismiss Message<br />" +
                        "</html>"
        );
        yBox = new YBox();
        yBox.add(label);
        yBox.addSpace(15, 0);
        yBox.setBorder(10);
        getContentPane().add(yBox, BorderLayout.CENTER);


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
        setTitle("About");
    }

    /*
    public void openWebsite(String website) {
        try {
            Desktop.getDesktop().browse(new URI("http://" + website));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainWindow.getInstance(), "Go to " + website, "Website", JOptionPane.INFORMATION_MESSAGE);
        }
    }*/
}
