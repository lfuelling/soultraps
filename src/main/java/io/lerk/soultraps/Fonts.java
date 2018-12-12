package io.lerk.soultraps;

import greenfoot.SoultrapsFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;

public class Fonts {

    private static final Logger log = LoggerFactory.getLogger(Fonts.class);
    private static final int DEFAULT_SIZE = 12;

    public static greenfoot.Font getFont(Types type) {
        return getFont(type, DEFAULT_SIZE);
    }

    public static greenfoot.Font getFont(Types type, float size) {
        try {
            java.awt.Font tmpFont = java.awt.Font.createFont(0, java.awt.Font.class.getResourceAsStream("/fonts/" + type.name + ".ttf"));
            return new SoultrapsFont(tmpFont.deriveFont(size));
        } catch (IOException | FontFormatException e) {
            log.error("Unable to load font!", e);
        }
        return null;
    }

    enum Types {

        SKYRIM("skyrim"),
        DAEDRA("daedra");

        private final String name;

        Types(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
