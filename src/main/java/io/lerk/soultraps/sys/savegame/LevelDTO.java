package io.lerk.soultraps.sys.savegame;

import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.levels.types.LevelType;
import javafx.util.Pair;

public class LevelDTO
{
  String[][] levelTiles = new String[Level.LEVEL_WIDTH][Level.LEVEL_HEIGHT];
  LevelType levelType;
  Pair<Integer, Integer> portalCoordinates;
  Pair<Integer, Integer> floppyCoordinates;

  public LevelDTO(String[][] levelTiles, LevelType levelType, Pair<Integer, Integer> portalCoordinates, Pair<Integer, Integer> floppyCoordinates)
  {
    this.levelTiles = levelTiles;
    this.levelType = levelType;
    this.portalCoordinates = portalCoordinates;
    this.floppyCoordinates = floppyCoordinates;
  }

  public LevelDTO()
  {
  }

  public String[][] getLevelTiles()
  {
    return levelTiles;
  }

  public void setLevelTiles(String[][] levelTiles)
  {
    this.levelTiles = levelTiles;
  }

  public LevelType getLevelType()
  {
    return levelType;
  }

  public void setLevelType(LevelType levelType)
  {
    this.levelType = levelType;
  }

  public Pair<Integer, Integer> getPortalCoordinates()
  {
    return portalCoordinates;
  }

  public void setPortalCoordinates(Pair<Integer, Integer> portalCoordinates)
  {
    this.portalCoordinates = portalCoordinates;
  }

  public Pair<Integer, Integer> getFloppyCoordinates()
  {
    return floppyCoordinates;
  }

  public void setFloppyCoordinates(Pair<Integer, Integer> floppyCoordinates)
  {
    this.floppyCoordinates = floppyCoordinates;
  }
}
