package fr.iutvalence.info.m4104.gildedroseinn;

public class GildedRose
{
    private static final int SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_THREE_TIMES = 5;
    private static final int SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_TWICE = 10;




    public static void updateItem(Item item)
    {
        updateItemQuality(item);
        updateItemSellIn(item);
    }

    private static void updateItemSellIn(Item item)
    {
        if (item.getName().equals("Sulfuras, Hand of Ragnaros"))
            return;
        decrementItemSellIn(item);
    }

    private static void updateItemQuality(Item item)
    {
        if (item.getName().equals("Sulfuras, Hand of Ragnaros"))
            return;

        if (item.getName().equals("Aged Brie"))
        {
            updateAgedBrieItemQuality(item);
            return;
        }

        if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
        {
            updateBackstageItemQuality(item);
            return;
        }

        if (item.getName().equals("Conjured Mana Cake"))
        {
            updateNormalItemQuality(item);
            return;
        }

        updateNormalItemQuality(item);
    }

    private static void updateBackstageItemQuality(Item item)
    {
        if (hasItemSellInDatePassed(item))
        {
            item.setQuality(0);
            return;
        }

        decrementItemQualityIfNotZero(item);
        if (item.getSellIn() <= SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_TWICE)
            decrementItemQualityIfNotZero(item);
        if (item.getSellIn() <= SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_THREE_TIMES)
            decrementItemQualityIfNotZero(item);
    }

    private static void updateNormalItemQuality(Item item)
    {
        decrementItemQualityIfNotZero(item);
        if (hasItemSellInDatePassed(item))
            decrementItemQualityIfNotZero(item);
    }

    private static void updateAgedBrieItemQuality(Item item)
    {
        incrementItemQualityIfNotFifty(item);
    }

    private static void incrementItemQualityIfNotFifty(Item item)
    {
        if (item.getQuality() < 50)
            incrementItemQuality(item);
    }

    private static void incrementItemQuality(Item item)
    {
        item.setQuality(item.getQuality() + 1);
    }

    private static void decrementItemQualityIfNotZero(Item item)
    {
        if (item.getQuality() > 0)
            decrementItemQuality(item);
    }

    private static boolean hasItemSellInDatePassed(Item item)
    {
        return item.getSellIn() <= 0;
    }

    private static void decrementItemSellIn(Item item)
    {
        item.setSellIn(item.getSellIn() - 1);
    }

    private static void decrementItemQuality(Item item)
    {
        item.setQuality(item.getQuality() - 1);
    }
}

