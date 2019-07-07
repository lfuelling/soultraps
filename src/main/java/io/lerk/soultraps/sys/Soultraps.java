package io.lerk.soultraps.sys;

import bluej.Config;
import greenfoot.Greenfoot;
import greenfoot.core.Simulation;
import greenfoot.export.GreenfootScenarioApplication;
import greenfoot.util.StandalonePropStringManager;
import io.lerk.soultraps.levels.menu.GameOverScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Main Soultraps application.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Soultraps extends GreenfootScenarioApplication
{

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

        launch(args);
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


    public static void gameOver() {
        Greenfoot.setWorld(new GameOverScreen());
    }

    public class Controls {
        public static final String CONV_START = "f";
        public static final String CONV_NEXT = "space";
        public static final String USE_ITEM = "r";
        public static final String DROP_ITEM = "t";
        public static final String SEL_ITEM_DN = "q";
        public static final String SEL_ITEM_UP = "e";
        public static final String WALK_UP = "w";
        public static final String WALK_DN = "s";
        public static final String WALK_LT = "a";
        public static final String WALK_RT = "d";
        public static final String ATK_UP = "up";
        public static final String ATK_DN = "down";
        public static final String ATK_LT = "left";
        public static final String ATK_RT = "right";
    }
}

