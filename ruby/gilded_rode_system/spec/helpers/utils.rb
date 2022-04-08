class ItemsFilter
    def initialize(items)
        @items = items
    end

    def normal_item
        @items[0]
    end

    def aged_brie_item
        @items[1]
    end

    def sulfuras_item
        @items[3]
    end

    def expired_normal_item
        item = self.normal_item()
        item.sell_in = 0
        item
    end

    def backstage_item_with(sell_in, quality)
        item = @items[4]
        item.sell_in = sell_in
        item.quality = quality
        item
    end

    def all
        @items
    end

    def all_without_sulfuras
        ItemsFilter.new self.reject_by_name("Sulfuras, Hand of Ragnaros")
    end

    def reject_by_name(name)
        @items.reject do |item|
            item.name == name
        end
    end

end