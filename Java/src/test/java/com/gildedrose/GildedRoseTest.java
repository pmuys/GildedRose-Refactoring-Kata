package com.gildedrose;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void theQualityOfBrieIncreases(){

        var item = new Item(GildedRose.AGED_BRIE,10,1);
        GildedRose.updateItem(item);
        assertEquals(2,item.quality);
    }

    @Test
    void theQualityForBrieIncreasesWith2AfterSelinIs0(){

        var item = new Item(GildedRose.AGED_BRIE,0,2);
        GildedRose.updateItem(item);
        assertEquals(4,item.quality);
    }
    @Test
    void theQualityOfBrieCanBeMaximum50(){

        var item = new Item(GildedRose.AGED_BRIE,10,50);
        GildedRose.updateItem(item);
        assertEquals(50,item.quality);
    }

    @Test
    void theSellByDateDoesNotChangeForSulfuras(){

        var item = new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS,123,10);
        GildedRose.updateItem(item);
        assertEquals(123, item.sellIn);
    }
    @Test
    void theQualityDoesNotChangeForSulfuras(){

        var item = new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS,123,10);
        GildedRose.updateItem(item);
        assertEquals(10, item.quality);
    }


    @Test
    void theSellByDateDecreasesOnUpdate(){

        var items = new Item[]{
            new Item(GildedRose.AGED_BRIE,2,10),
            new Item(GildedRose.BACKSTAGE_PASSES,1,10),
            new Item("Normal Item",0,10),
        };
        GildedRose.updateQuality(items);
        assertEquals(1, items[0].sellIn);
        assertEquals(0, items[1].sellIn);
        assertEquals(-1, items[2].sellIn);
    }

    @Test
    void theQualityOfAnItemDegradesTwiceAsFastAfterTheSellByDate(){

        var item = new Item("Normal Item",1,10);
        GildedRose.updateItem(item);
        assertEquals(10-1, item.quality);
        GildedRose.updateItem(item);
        assertEquals(10-1-2, item.quality);
    }

    @Test
    void theQualityOfABackstagePassIs0AfterConcert(){

        var item = new Item(GildedRose.BACKSTAGE_PASSES,0,10);
        GildedRose.updateItem(item);
        assertEquals(0,item.quality);
    }

    @Test
    void theQualityOfABackstagePassIncreasesBy1WhenMoreThen10Days(){

        var item = new Item(GildedRose.BACKSTAGE_PASSES,11,10);
        GildedRose.updateItem(item);
        assertEquals(11,item.quality);
    }

    @Test
    void theQualityOfABackstagePassIncreasesBy2When10DaysOrLess(){

        var item = new Item(GildedRose.BACKSTAGE_PASSES,10,10);
        GildedRose.updateItem(item);
        assertEquals(12,item.quality);
    }
    @Test
    void theQualityOfABackstagePassIncreasesBy3When5DaysOrLess(){

        var item = new Item(GildedRose.BACKSTAGE_PASSES,4,10);
        GildedRose.updateItem(item);
        assertEquals(13,item.quality);
    }
}
