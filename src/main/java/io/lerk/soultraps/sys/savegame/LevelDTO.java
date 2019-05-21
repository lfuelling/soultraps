package io.lerk.soultraps.sys.savegame;

import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.levels.types.LevelType;

public class LevelDTO
{
  String[][] levelTiles = new String[Level.LEVEL_WIDTH][Level.LEVEL_HEIGHT];
  LevelType levelType;

  public LevelDTO(String[][] levelTiles, LevelType levelType)
  {
    this.levelTiles = levelTiles;
    this.levelType = levelType;
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
}
