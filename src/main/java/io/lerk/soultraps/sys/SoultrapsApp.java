package io.lerk.soultraps.sys;

import bluej.Config;
import greenfoot.Greenfoot;
import greenfoot.export.GreenfootScenarioViewer;
import greenfoot.util.StandalonePropStringManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Main Soultraps application.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class SoultrapsApp {

    /**
     * This is used to enable debug stuff like button backgrounds.
     */
    public static final boolean DEBUG = true;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(SoultrapsApp.class);

    /**
     * The properties.
     */
    private static final Properties properties = new Properties();

    /**
     * Main method.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) {
        // load properties
        initProperties();
        String scenarioName = properties.getProperty("project.name");

        // macOS stuff
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", scenarioName);

        // window references

        final JFrame frame = new JFrame();
        final GreenfootScenarioViewer gsv = new GreenfootScenarioViewer(frame);
        gsv.init();
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        URL resource = SoultrapsApp.class.getClassLoader().getResource("images/logo.png");
        if (resource != null) {
            ImageIcon icon = new ImageIcon(resource);
            frame.setIconImage(icon.getImage());
        }

        frame.pack();
        frame.setVisible(true);
        if (Config.getPropBoolean("scenario.hideControls", false)) {
            Greenfoot.start();
        }

    }

    /**
     * Initializes project properties.
     */
    private static void initProperties() {
        try (InputStream is = SoultrapsApp.class.getClassLoader().getResourceAsStream("standalone.properties")) {
            properties.load(is);
            Config.initializeStandalone(new StandalonePropStringManager(properties));
        } catch (IOException e) {
            log.error("Error loading properties!", e);
        }
    }


}

