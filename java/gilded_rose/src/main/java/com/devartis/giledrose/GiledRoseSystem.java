package com.devartis.giledrose;

public class GiledRoseSystem {
	private Item[] items;

	public GiledRoseSystem() {
		items = new Item[]{
				new Item("+5 Dexterity Vest", 10, 20),
				new Item("Aged Brie", 2, 0),
				new Item("Elixir of the Mongoose", 5, 7),
				new Item("Sulfuras, Hand of Ragnaros", 0, 80),
				new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
				new Item("Conjured Mana Cake", 3, 6)
		};

	}

	public Item[] getItems() {
		return items;
	}

	/**
	* The goal of this method is to update all the items tracked by the System.
	* See GiledRoseSystemTest class.
	* */
	public void endDayUpdate() {
		throw new RuntimeException("Not implemented yet");
	}
}