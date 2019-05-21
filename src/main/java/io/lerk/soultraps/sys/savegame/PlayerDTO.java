package io.lerk.soultraps.sys.savegame;

import java.util.ArrayList;

public class PlayerDTO
{
  ArrayList<String> items;
  int posX, posY;

  public PlayerDTO(ArrayList<String> items, int posX, int posY)
  {
    this.items = items;
    this.posX = posX;
    this.posY = posY;
  }

  public PlayerDTO()
  {
  }

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
}
