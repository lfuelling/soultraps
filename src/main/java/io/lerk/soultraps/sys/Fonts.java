package io.lerk.soultraps.sys;

import greenfoot.SoultrapsFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

/**
 * Utility class for fonts.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Fonts {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Fonts.class);

    /**
     * Default font size.
     */
    private static final int DEFAULT_SIZE = 12;

    /**
     * Gets a font by {@link Types}.
     *
     * @param type the font type.
     * @return the font.
     */
    public static greenfoot.Font getFont(Types type) {
        return getFont(type, DEFAULT_SIZE);
    }

    /**
     * Gets a font by {@link Types} with a particular size.
     *
     * @param type the font type.
     * @param size the font size
     * @return the font.
     */
    public static greenfoot.Font getFont(Types type, float size) {
        try {
            java.awt.Font tmpFont = java.awt.Font.createFont(0, java.awt.Font.class.getResourceAsStream("/fonts/" + type.name + ".ttf"));
            return new SoultrapsFont(tmpFont.deriveFont(size));
        } catch (IOException | FontFormatException e) {
            log.error("Unable to load font!", e);
        }
        return null;
    }

    /**
     * Font types.
     */
    public enum Types {

        /**
         * The regular skyrim font.
         */
        SKYRIM("skyrim"),

        /**
         * The daedra font.
         */
        DAEDRA("daedra");

        /**
         * Font name.
         */
        private final String name;

        /**
         * Constructor.
         *
         * @param name font name
         */
        Types(String name) {
            this.name = name;
        }

        /**
         * Getter for font name.
         *
         * @return font name
         */
        public String getName() {
            return name;
        }
    }
}
