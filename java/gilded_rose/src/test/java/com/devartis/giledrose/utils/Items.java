package com.devartis.giledrose.utils;

import com.devartis.giledrose.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class provided only for testing purposes.
 *
 * Facilitates the creation of different scenarios for unit testing the GiledRoseSystem class.
 * */
public class Items {

    private Item[] items;

    public Items(Item[] items) {
        this.items = items;
    }

    public Item aNormalItem() {
        return items[0];
    }

    public Item agedBrieItem() {
        return items[1];
    }

    public Item sulfurasItem() {
        return items[3];
    }

    public Item anExpiredNormalItem() {
        Item expiredNormalItem = aNormalItem();
        expiredNormalItem.setSellIn(0);
        return expiredNormalItem;
    }

    public Item backstageItemWith(int sellIn, int quality) {
        Item backstageItem = items[4];

        backstageItem.setSellIn(sellIn);
        backstageItem.setQuality(quality);

        return backstageItem;
    }

    public Item[] getAll() {
        return items;
    }

    public Items allWithoutSulfuras() {
        return new Items(rejectNamed("Sulfuras, Hand of Ragnaros"));
    }

    private Item[] rejectNamed(String nameToReject) {
        List<Item> nonRejectedItems = new ArrayList<Item>();

        for (Item item : nonRejectedItems) {
            if (!item.getName().equals(nameToReject)) {
                nonRejectedItems.add(item);
            }
        }

        return nonRejectedItems.toArray(new Item[nonRejectedItems.size()]);
    }

}
