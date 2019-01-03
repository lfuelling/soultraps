package io.lerk.soultraps.sys;

import com.google.gson.Gson;
import io.lerk.soultraps.mobs.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Savegame class.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Savegame {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(Savegame.class);

    /**
     * Level tiles.
     */
    private final String[][] levelTiles;

    /**
     * Player object.
     */
    private final Player player;

    /**
     * Constructor.
     *
     * @param levelTiles level content
     * @param player     player
     */
    public Savegame(String[][] levelTiles, Player player) {
        this.levelTiles = levelTiles;
        this.player = player;
    }

    /**
     * Writes a savegame to the default location.
     */
    public void write() {
        try (FileWriter fw = new FileWriter(getSavegameDir() + "/save.json")) {
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(new Gson().toJson(this));
            } catch (IOException e) {
                logger.error("Unable to write to savegame file.", e);
            }
        } catch (IOException e) {
            logger.error("Unable to open savegame file.", e);
        }

    }

    /**
     * Method to find savedir cross-platform.
     *
     * @return the savegame dir
     */
    private String getSavegameDir() {
        String savegameDir;
        String os = (System.getProperty("os.name")).toLowerCase();
        if (os.contains("WIN")) {
            savegameDir = System.getenv("AppData");
        } else {
            savegameDir = System.getProperty("user.home");
            if (os.contains("mac")) {
                savegameDir += "/Library/Application Support";
            } else if (os.contains("linux")) {
                savegameDir += ".config";
            }
        }
        savegameDir += "/soultraps";
        return savegameDir;
    }
}
