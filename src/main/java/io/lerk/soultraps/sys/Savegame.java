package io.lerk.soultraps.sys;

import com.google.gson.Gson;
import greenfoot.Greenfoot;
import io.lerk.soultraps.items.Item;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.levels.menu.Launcher;
import io.lerk.soultraps.levels.playable.RegularDesertLevel;
import io.lerk.soultraps.levels.playable.RegularGrasslandLevel;
import io.lerk.soultraps.levels.types.DesertLevel;
import io.lerk.soultraps.levels.types.GrasslandLevel;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.savegame.LevelDTO;
import io.lerk.soultraps.sys.savegame.PlayerDTO;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final LevelDTO level;

    /**
     * Player object.
     */
    private final PlayerDTO player;

    private static boolean errorShown = false;

    /**
     * Constructor.
     *
     * @param level  level
     * @param player player
     */
    public Savegame(Level level, Player player) {
        this.level = fromLevel(level);
        this.player = fromPlayer(player);

        if(errorShown) {
            return;
        }
        File saveFile = new File(saveFileName);
        if (!saveFile.exists()) {
            if(!new File(getSavegameDir()).exists()) {
                if(!new File(getSavegameDir()).canWrite()) {
                    errorShown = true;
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The savegame directory (" + getSavegameDir() + ") is not writable! The application will now exit.", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.setOnCloseRequest((e) -> System.exit(1));
                        alert.show();
                    });
                }
                if(new File(getSavegameDir()).mkdirs()) {
                    logger.info("Savegame directory cerated!");
                }
            }
            try {
                logger.info("Savefile doesn't exist. Creating directories and file!");
                logger.debug("File" + ((saveFile.createNewFile()) ? "" : " not") + " created.");
            } catch (IOException e) {
                logger.error("Error creating savegame file.", e);
            }
        }
    }

    private PlayerDTO fromPlayer(Player player)
    {
        List<String> collect = player.getItems().stream()
          .map(Item::getName)
          .collect(Collectors.toList());

        return new PlayerDTO(new ArrayList<>(collect), player.getX(), player.getY());
    }

    private LevelDTO fromLevel(Level level)
    {
        return new LevelDTO(
          level.getLevelTiles(),
          level.getType(),
          level.getPortalCoordinates(),
          level.getFloppyCoordinates());
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
                savegameDir += "/.config";
            }
        }
        savegameDir += "/soultraps";
        return savegameDir;
    }

    public Level getLevel() {
        switch(level.getLevelType()) {
            case GRASS:
                return new RegularGrasslandLevel(level.getLevelTiles(), level.getPortalCoordinates(), level.getFloppyCoordinates());
            case DESERT:
                return new RegularDesertLevel(level.getLevelTiles(), level.getPortalCoordinates(), level.getFloppyCoordinates());
            case MENU:
                return new Launcher();
        }
        throw new IllegalStateException("Cannot load level of type " + level.getLevelType() + "!");
    }

    public void restorePlayer() {
        Player.restore(player);
    }

    public static boolean isErrorShown()
    {
        return errorShown;
    }
}
