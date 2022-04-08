from random import randrange
import unittest

from gilded_rose import GildedRoseSystem

class TestGildedRoseSystem(unittest.TestCase):
    def setUp(self):
        self.gilded_rose_system = GildedRoseSystem()
        self.items = ItemsFilter(self.gilded_rose_system.items)

    def test_decreases_sell_in(self):
        item = self.items.normal_item()
        previous_sell_in = item.sell_in
        self.gilded_rose_system.end_day_update()
        self.assertEquals(previous_sell_in - 1, item.sell_in)

    def test_decreases_quality(self):
        item = self.items.normal_item()
        previous_quality = item.quality
        self.gilded_rose_system.end_day_update()
        self.assertEquals(previous_quality - 1, item.quality)

    def test_increases_Aged_Brie(self):
      item = self.items.aged_brie_item()
      previous_quality = item.quality
      self.gilded_rose_system.end_day_update()
      self.assertEquals(previous_quality + 1, item.quality)

    def test_always_has_quality_above_or_equal_to_0(self):
      self.assert_quality_above_or_equal_0_for_all(self.items)
      self.run_end_day_update_many_times(self.gilded_rose_system)
      self.assert_quality_above_or_equal_0_for_all(self.items)

    def test_keeps_quality_below_or_equals_to_50_but_selfuras(self):
      self.assert_quality_below_or_equal_50_for_all(self.items.all_without_sulfuras())
      self.run_end_day_update_many_times(self.gilded_rose_system)
      self.assert_quality_below_or_equal_50_for_all(self.items.all_without_sulfuras())

    def test_descreses_quality_twice_when_sell_in_is_0(self):
      item = self.items.expired_normal_item()
      previous_quality = item.quality
      self.gilded_rose_system.end_day_update()
      self.assertEquals(previous_quality - 2, item.quality)

    def test_does_not_change_sulfura_quality(self):
      item = self.items.sulfuras_item()
      previous_quality = item.quality
      self.run_end_day_update_many_times(self.gilded_rose_system)
      self.assertEquals(previous_quality, item.quality)

    def test_increses_quality_twice_when_there_re_10_days_or_less(self):
      quality = self.random_integer()
      item = self.items.backstage_item_with(10, quality)
      self.run_end_day_update_five_times(self.gilded_rose_system)
      self.assertEquals(quality + (5 * 2), item.quality)

    def test_increses_quality_3_times_when_there_re_5_days_or_less(self):
      quality = self.random_integer()
      item = self.items.backstage_item_with(5, quality)
      self.run_end_day_update_five_times(self.gilded_rose_system)
      self.assertEquals(quality + (5 * 3), item.quality)

    def test_drops_backstage_quality_to_0_when_sell_in_is_0(self):
      quality = self.random_integer()
      item = self.items.backstage_item_with(5, quality)
      self.run_end_day_update_many_times(self.gilded_rose_system)
      self.assertEquals(0, item.quality)

    # Helper methods!

    def assert_quality_above_or_equal_0_for_all(self, items):
        for item in items.all():
            self.assertGreaterEqual(item.quality, 0)
        
    def run_end_day_update_many_times(self, gilded_rose_system):
        self.run_end_day_update_times(gilded_rose_system, 40)

    def run_end_day_update_five_times(self, gilded_rose_system):
        self.run_end_day_update_times(gilded_rose_system, 5)
    
    def run_end_day_update_times(self, gilded_rose_system, times):
        for _ in range(times):
            gilded_rose_system.end_day_update()
    
    def assert_quality_below_or_equal_50_for_all(self, items):
        for item in items.all():
            self.assertLessEqual(item.quality, 50)

    def random_integer(self):
        return randrange(0, 31)


class ItemsFilter:
    """
    Helper class. Do not modify
    """
    def __init__(self, items):
        self.items = items

    def normal_item(self):
        return self.items[0]

    def aged_brie_item(self):
        return self.items[1]

    def sulfuras_item(self):
        return self.items[3]

    def expired_normal_item(self):
        item = self.normal_item()
        item.sell_in = 0
        return item

    def backstage_item_with(self, sell_in, quality):
        item = self.items[4]
        item.sell_in = sell_in
        item.quality = quality
        return item

    def all(self):
        return self.items

    def all_without_sulfuras(self):
        return ItemsFilter(self.reject_by_name("Sulfuras, Hand of Ragnaros"))

    def reject_by_name(self, name):
        return list([item for item in self.items if item.name != name])

if __name__ == '__main__':
    unittest.main()