package io.lerk.soultraps.sys;

import bluej.Config;
import com.apple.eawt.Application;
import greenfoot.Greenfoot;
import greenfoot.export.GreenfootScenarioViewer;
import greenfoot.util.StandalonePropStringManager;
import io.lerk.soultraps.sys.window.AboutWindow;
import io.lerk.soultraps.sys.window.PreferenceWindow;
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
public class Soultraps {

    /**
     * This is used to enable debug stuff like button backgrounds.
     */
    public static final boolean DEBUG = false;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Soultraps.class);

    /**
     * The properties.
     */
    private static final Properties properties = new Properties();

    /**
     * Main method.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Soultraps");
        System.setProperty("apple.awt.application.name", "Soultraps");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // load properties
        initProperties();

        // window references
        final JFrame frame = new JFrame();
        final GreenfootScenarioViewer gsv = new GreenfootScenarioViewer(frame);
        gsv.init();
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        URL resource = Soultraps.class.getClassLoader().getResource("images/logo.png");
        if (resource != null) {
            ImageIcon icon = new ImageIcon(resource);
            frame.setIconImage(icon.getImage());
        }

        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            // macOS stuff
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            if (resource != null) {
                Application.getApplication().setDockIconImage(new ImageIcon(resource).getImage());
            }

            Application.getApplication().setPreferencesHandler(pe -> new PreferenceWindow().setVisible(true));
            Application.getApplication().setAboutHandler(aboutEvent -> new AboutWindow().setVisible(true));
            Application.getApplication().setQuitHandler((quitEvent, quitResponse) -> System.exit(0));
        }
        frame.setTitle("Soultraps");
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
        try (InputStream is = Soultraps.class.getClassLoader().getResourceAsStream("standalone.properties")) {
            properties.load(is);
            Config.initializeStandalone(new StandalonePropStringManager(properties));
        } catch (IOException e) {
            log.error("Error loading properties!", e);
        }
    }


}
