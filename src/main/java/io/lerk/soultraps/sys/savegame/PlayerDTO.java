package io.lerk.soultraps.sys.savegame;

import java.util.ArrayList;

public class PlayerDTO
{
  final ArrayList<String> items;
  final int posX, posY, health;
  final boolean drankGoldenDistillate;

  public PlayerDTO(ArrayList<String> items, int posX, int posY, int health, boolean drankGoldenDistillate)
  {
    this.items = items;
    this.posX = posX;
    this.posY = posY;
    this.health = health;
    this.drankGoldenDistillate = drankGoldenDistillate;
  }

  public ArrayList<String> getItems()
  {
    return items;
  }

  public int getPosX()
  {
    return posX;
  }

  public int getPosY()
  {
    return posY;
  }

  public int getHealth() {
    return health;
  }

  public boolean drankGoldenDistillate() {
    return drankGoldenDistillate;
  }
}
