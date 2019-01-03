package io.lerk.soultraps.sys.window;

import nl.paulinternet.gtasaveedit.view.swing.YBox;

import javax.swing.*;
import java.awt.*;

/**
 * This is a simple preference window.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class PreferenceWindow extends JFrame {

    /**
     * The window's main content.
     */
    private YBox yBox;

    /**
     * Constructor.
     */
    public PreferenceWindow() {

        yBox = new YBox();
        yBox.add(new JLabel("TODO"));
        yBox.addSpace(15, 0);
        yBox.setBorder(10);
        getContentPane().add(yBox, BorderLayout.CENTER);


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
        setTitle("Preferences");
    }
}
