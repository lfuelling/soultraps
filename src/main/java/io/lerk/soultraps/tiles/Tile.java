package io.lerk.soultraps.tiles;

/**
 * Base interface for tile.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public interface Tile {

    /**
     * Get the tile name. This appended by {@link Tiles#FILE_SUFFIX}
     * is the tiles image filename relative to the /resources/images folder.
     *
     * @return the name of the tile
     */
    String getName();

    /**
     * Getter used to decide if the player should be able
     * to step across/on top of/over the tile.
     *
     * @return true if the player is unable to step on the tile
     */
    Boolean isBlocking();
}
