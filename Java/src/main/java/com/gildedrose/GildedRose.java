package com.gildedrose;


public class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    public static final int MAX_QUALITY = 50;

    private GildedRose() {
    }

    private static void updateAgedBrie(Item item) {

        if (item.sellIn <= 0) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }

        item.quality = Math.min(MAX_QUALITY, item.quality);
        item.sellIn -= 1;
    }

    private static void updateNormalItem(Item item) {

        if (item.sellIn <= 0) {
            item.quality -= 2;
        } else {
            item.quality -= 1;
        }
        item.quality = Math.max(0, item.quality);
        item.sellIn -= 1;
    }

    private static void updateBackstagePasses(Item item) {

        if (item.sellIn <= 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        } else {
            item.quality += 1;
        }
        item.quality = Math.min(MAX_QUALITY, item.quality);
        item.sellIn -= 1;
    }

    private static void updateConjuredManaCake(Item item) {

        item.quality = Math.max(0, item.quality - 2);
        item.sellIn -= 1;
    }

    static Item updateItem(Item item) {

        switch (item.name) {
            case AGED_BRIE:
                updateAgedBrie(item);
                break;
            case BACKSTAGE_PASSES:
                updateBackstagePasses(item);
                break;
            case CONJURED_MANA_CAKE:
                updateConjuredManaCake(item);
                break;
            case SULFURAS_HAND_OF_RAGNAROS:
                break;
            default:
                updateNormalItem(item);
        }
        return item;
    }

    public static void updateQuality(Item[] items) {

        for (var item : items) {
            updateItem(item);
        }

    }
}
