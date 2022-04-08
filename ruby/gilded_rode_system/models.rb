class GildedRoseSystem
    attr_reader :items

    def initialize
        @items = [
            ["+5 Dexterity Vest", 10, 20],
            ["Aged Brie", 2, 0],
            ["Elixir of the Mongoose", 5, 7],
            ["Sulfuras, Hand of Ragnaros", 0, 80],
            ["Backstage passes to a TAFKAL80ETC concert", 15, 20],
            ["Conjured Mana Cake", 3, 6],
        ].map do |name, sell_in, quality|
            Item.new name, sell_in, quality
        end
    end

    def end_day_update
        raise NotImplementedError, "#end_day_update not implemented yet!"
    end
    

end


class Item
    attr_accessor :name, :sell_in, :quality
    def initialize(name, sell_in, quality)
        self.name = name
        self.sell_in = sell_in
        self.quality = quality
    end
end
