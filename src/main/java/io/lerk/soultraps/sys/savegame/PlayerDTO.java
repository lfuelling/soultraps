package io.lerk.soultraps.sys.savegame;

import java.util.ArrayList;

public class PlayerDTO
{
  ArrayList<String> items;
  int posX, posY;
  boolean drankGoldenDistillate;

  public PlayerDTO(ArrayList<String> items, int posX, int posY, boolean drankGoldenDistillate)
  {
    this.items = items;
    this.posX = posX;
    this.posY = posY;
    this.drankGoldenDistillate = drankGoldenDistillate;
  }

  public PlayerDTO()
  {}

  public ArrayList<String> getItems()
  {
    return items;
  }

  public void setItems(ArrayList<String> items)
  {
    this.items = items;
  }

  public int getPosX()
  {
    return posX;
  }

  public void setPosX(int posX)
  {
    this.posX = posX;
  }

  public int getPosY()
  {
    return posY;
  }

  public void setPosY(int posY)
  {
    this.posY = posY;
  }

  public boolean drankGoldenDistillate() {
    return drankGoldenDistillate;
  }

  public void setDrankGoldenDistillate(boolean drankGoldenDistillate) {
    this.drankGoldenDistillate = drankGoldenDistillate;
  }
}
