ITEMS_FIXTURE = [
    ["+5 Dexterity Vest", 10, 20],
    ["Aged Brie", 2, 0],
    ["Elixir of the Mongoose", 5, 7],
    ["Sulfuras, Hand of Ragnaros", 0, 80],
    ["Backstage passes to a TAFKAL80ETC concert", 15, 20],
    ["Conjured Mana Cake", 3, 6],
]

class GildedRoseSystem:
    def __init__(self):
        self.items = list([Item(name, sell_in, quality) for name, sell_in, quality in ITEMS_FIXTURE])

    def end_day_update(self):
        raise Exception("not implemented yet.")


class Item:
    def __init__(self, name, sell_in, quality):
        self.name = name
        self.sell_in = sell_in
        self.quality = quality
