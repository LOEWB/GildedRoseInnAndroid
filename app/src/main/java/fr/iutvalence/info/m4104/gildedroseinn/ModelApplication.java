package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by loewb on 05/02/16.
 */
public class ModelApplication extends Application
{
    private final static Item[] items = new Item[]
            {
             new Item("+5 Dexterity Vest", 10, 20),
             new Item("Aged Brie", 2, 0),
             new Item("Elixir of the Mongoose", 5, 7),
             new Item("Sulfuras, Hand of Ragnaros", 0, 80),
             new Item("Sulfuras, Hand of Ragnaros", -1, 80),
             new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
             new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
             new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
             new Item("Conjured Mana Cake", 3, 6) };

    private static ArrayList<Item> inventoryList=new ArrayList<Item>();

    private static int dayNb=0;

    private static float wallet=0;

    public static float getWallet() {
        return wallet;
    }

    public static void incrWallet() {
        ModelApplication.wallet += 100;
    }

    public static void setWallet(float i)
    {
        wallet=i;
    }

    public static void substracWallet(float i)
    {
        ModelApplication.wallet -= i;
    }
    public static int getDayNb()
    {
        return dayNb;
    }

    public static void incrDayNb()
    {
        dayNb++;
    }
    public ModelApplication(){};

    public Item getItem(int posi)
    {
        return this.items[posi];
    }

    public int getItemsSize()
    {
        return this.items.length;
    }

    public Item getItemInventory(int posi){return inventoryList.get(posi); }

    public void addItemToInventory(Item item)
    {
        inventoryList.add(item);
    }

    public void removeFromInventory(int i)
    {
        inventoryList.remove(i);
    }

    public int getInventorySize() {
        return this.inventoryList.size();
    }

    public void updateAllItems()
    {
        //update des items du shop
        for(int i=0;i<items.length;i++)
        {
            GildedRose.updateItem(this.getItem(i));
        }
        //update des items de l'inventaire
        for(int i=0;i<inventoryList.size();i++)
        {
            GildedRose.updateItem(this.getItemInventory(i));
        }
    }
}
