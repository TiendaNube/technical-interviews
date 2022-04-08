package com.devartis.giledrose;

import com.devartis.giledrose.utils.Items;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GiledRoseSystemTest {
	private GiledRoseSystem giledRoseSystem;

	// Class provided only for testing purposes.
	private Items items;

	@Before
	public void setUp() {
		giledRoseSystem = new GiledRoseSystem();
		items = new Items(giledRoseSystem.getItems());
	}

	@Test
	public void theSellInValueDecrease() {
		Item normalItem = items.aNormalItem();
		int previousSellIn = normalItem.getSellIn();

		giledRoseSystem.endDayUpdate();

		assertEquals(previousSellIn - 1, normalItem.getSellIn());
	}

	@Test
	public void theQualityValueDecrease() {
		Item normalItem = items.aNormalItem();
		int previousQuality = normalItem.getQuality();

		giledRoseSystem.endDayUpdate();

		assertEquals(previousQuality - 1, normalItem.getQuality());
	}

	@Test
	public void theAgedBrieQualityIncrease() {
		Item agedBrie = items.agedBrieItem();
		int previousQuality = agedBrie.getQuality();

		giledRoseSystem.endDayUpdate();

		assertEquals(previousQuality + 1, agedBrie.getQuality());
	}

	@Test
	public void theQualityValueIsAlwaysAboveOrEqual0() {
		assertQualityAboveOrEqual0ForAll(items);

		runEndOfDayUpdatesManyTimes();

		assertQualityAboveOrEqual0ForAll(items);
	}

	@Test
	public void theQualityValueIsAlwaysBelowOrEqual50ExceptSulfuras() {
		assertQualityBelowOrEqual50ForAll(items.allWithoutSulfuras());

		runEndOfDayUpdatesManyTimes();

		assertQualityBelowOrEqual50ForAll(items.allWithoutSulfuras());
	}

	@Test
	public void theQualityValueDecreaseTwiceWhenSellInIs0() {
		Item expiredNormalItem = items.anExpiredNormalItem();
		int previousQuality = expiredNormalItem.getQuality();

		giledRoseSystem.endDayUpdate();

		assertEquals(previousQuality - 2, expiredNormalItem.getQuality());
	}

	@Test
	public void sulfurasQualityIsConstant() {
		Item sulfurasItem = items.sulfurasItem();
		int previousQuality = sulfurasItem.getQuality();

		runEndOfDayUpdatesManyTimes();

		assertEquals(previousQuality, sulfurasItem.getQuality());
	}

	@Test
	public void theBackstageQualityIncreasesBy2WhenThereAre10DaysOrLess() {
		int initialQuality = aPositiveInteger();
		Item backstageItem = items.backstageItemWith(sellIn(10), quality(initialQuality));

		runEndOfDayUpdateFiveTimes();

		assertEquals(initialQuality + (5 * 2), backstageItem.getQuality());
	}

	@Test
	public void backstageQualityIncreasesBy3WhenThereAre5DaysOrLess() {
		int initialQuality = aPositiveInteger();
		Item backstageItem = items.backstageItemWith(sellIn(5), quality(initialQuality));

		runEndOfDayUpdateFiveTimes();

		assertEquals(initialQuality + (5 * 3), backstageItem.getQuality());
	}

	@Test
	public void backstageQualityDropsTo0WhenSellInIs0() {
		int initialQuality = aPositiveInteger();
		Item backstageItem = items.backstageItemWith(sellIn(5), quality(initialQuality));

		runEndOfDayUpdatesManyTimes();

		assertEquals(0, backstageItem.getQuality());
	}

	/**
	 * Utility methods for helping in the testing process.
	 * */
	private int aPositiveInteger() {
		return 1 + (int) (Math.random() * ((30 - 1) + 1));
	}

	private int quality(int i) {
		return i;
	}

	private int sellIn(int i) {
		return i;
	}

	private void runEndOfDayUpdatesManyTimes() {
		runEndOfDayUpdateTimes(40);
	}

	private void runEndOfDayUpdateFiveTimes() {
		runEndOfDayUpdateTimes(5);
	}

	private void runEndOfDayUpdateTimes(int times) {
		for (int i = 0; i < times; i++) {
			giledRoseSystem.endDayUpdate();
		}
	}

	private void assertQualityAboveOrEqual0ForAll(Items items) {
		for (Item item : items.getAll()) {
			assertTrue(item.getQuality() >= 0);
		}
	}

	private void assertQualityBelowOrEqual50ForAll(Items items) {
		for (Item item : items.getAll()) {
			assertTrue(item.getQuality() <= 50);
		}
	}
}