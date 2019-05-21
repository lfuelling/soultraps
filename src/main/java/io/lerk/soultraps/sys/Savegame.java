package io.lerk.soultraps.sys;

import com.google.gson.Gson;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.mobs.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Savegame class.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Savegame {

    /**
     * Savefile name.
     */
    private static final String saveFileName = getSavegameDir() + "/save.json";

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Savegame.class);

    /**
     * Level tiles.
     */
    private final Level level;

    /**
     * Player object.
     */
    private final Player player;

    /**
     * Constructor.
     *
     * @param level  level
     * @param player player
     */
    public Savegame(Level level, Player player) {
        //FIXME replace level and player with daos to prevent gson StackOverflowError
        this.level = level;
        this.player = player;
        File saveFile = new File(saveFileName);
        if (!saveFile.exists()) {
            if(!new File(getSavegameDir()).exists()) {
                if(new File(getSavegameDir()).mkdirs()) {
                    logger.info("Savegame directory cerated!");
                }
            }
            try {
                logger.info("Savefile doesn't exist. Creating directories and file!");
                logger.debug("MkDirs" + ((new File(saveFile.getParent()).mkdirs()) ? "" : " not") + " successful. ");
                logger.debug("File" + ((saveFile.createNewFile()) ? "" : " not") + " created.");
            } catch (IOException e) {
                logger.error("Error creating savegame file.", e);
            }
        }
    }

    /**
     * Writes a {@link Savegame} to the default location.
     */
    public void write() {
        try (FileWriter fw = new FileWriter(saveFileName)) {
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
     * Reads a {@link Savegame} from the default location.
     *
     * @return the parsed savegame
     */
    public static Savegame read() {
        try (FileReader fr = new FileReader(saveFileName)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                Savegame savegame = new Gson().fromJson(br, Savegame.class);
                if (savegame != null) {
                    return savegame;
                } else {
                    logger.error("error parsing savegame file.", new Throwable("json error"));
                }
            } catch (IOException e) {
                logger.error("Unable to read to savegame file.", e);
            }
        } catch (IOException e) {
            logger.warn("Unable to open savegame file.", e);
        }
        return null;
    }

    /**
     * Method to find savedir cross-platform.
     *
     * @return the savegame dir
     */
    private static String getSavegameDir() {
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

    public Level getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }
}
