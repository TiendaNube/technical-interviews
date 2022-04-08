require_relative '../models.rb'
require_relative './helpers/utils.rb'

RSpec.describe GildedRoseSystem do
  before(:example) do
    @gilded_rose_system = described_class.new()
    @items = ItemsFilter.new(@gilded_rose_system.items)
  end

  describe "#end_day_update" do
    it "descreases sell in" do
      item = @items.normal_item
      previous_sell_in = item.sell_in
      @gilded_rose_system.end_day_update
      expect(item.sell_in).to eq(previous_sell_in - 1)
    end

    it "decreases quality" do
      item = @items.normal_item
      previous_quality = item.quality
      @gilded_rose_system.end_day_update
      expect(item.quality).to eq(previous_quality - 1)
    end

    it "increases Aged Brie" do
      item = @items.aged_brie_item
      previous_quality = item.quality
      @gilded_rose_system.end_day_update
      expect(item.quality).to eq(previous_quality + 1)
    end

    it "always has quality above or equal to 0" do
      expect_quality_above_or_equal_0_for_all(@items)
      run_end_day_update_many_times(@gilded_rose_system)
      expect_quality_above_or_equal_0_for_all(@items)
    end

    it "keeps quality below or equals to 50 but selfuras" do
      expect_quality_below_or_equal_50_for_all(@items.all_without_sulfuras)
      run_end_day_update_many_times(@gilded_rose_system)
      expect_quality_below_or_equal_50_for_all(@items.all_without_sulfuras)
    end

    it "descreses quality twice when sell in is 0" do
      item = @items.expired_normal_item
      previous_quality = item.quality
      @gilded_rose_system.end_day_update
      expect(item.quality).to eq(previous_quality - 2)
    end

    it "does not change sulfura quality" do
      item = @items.sulfuras_item
      previous_quality = item.quality
      run_end_day_update_many_times(@gilded_rose_system)
      expect(item.quality).to eq(previous_quality)
    end

    it "increses quality twice when there're 10 days or less" do
      quality = random_integer
      item = @items.backstage_item_with(10, quality)
      run_end_day_update_five_times @gilded_rose_system
      expect(item.quality).to eq(quality + (5 * 2))

    end

    it "increses quality 3 times when there're 5 days or less" do
      quality = random_integer
      item = @items.backstage_item_with(5, quality)
      run_end_day_update_five_times @gilded_rose_system
      expect(item.quality).to eq(quality + (5 * 3))

    end

    it "drops backstage quality to 0 when sell in is 0" do
      quality = random_integer
      item = @items.backstage_item_with(5, quality)
      run_end_day_update_many_times @gilded_rose_system
      expect(item.quality).to eq(0)
    end


  end

end
