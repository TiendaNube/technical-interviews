# Gilded Rose

Hi and welcome to team Gilded Rose.

As you know, we are a small inn with a prime location in a prominent city ran by a friendly innkeeper
named Allison. We also buy and sell only the finest goods.

Unfortunately, our goods are constantly degrading in quality as they approach their sell by date.

First an introduction to our system:

- All items have a SellIn value which denotes the number of days we have to sell the item
- All items have a Quality value which denotes how valuable the item is
- At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:

- Once the sell by date has passed, Quality degrades twice as fast
- The Quality of an item is never negative
- "Aged Brie" actually increases in Quality the older it gets
- The Quality of an item is never more than 50
- "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
- "Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches;
  - Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less
  - but Quality drops to 0 after the concert
- "Conjured" items degrade in Quality twice as fast as normal items

Do not alter the Item class or items property as those belong to the goblin
in the corner who will insta-rage and one-shot you as he doesn't believe in shared
code ownership.

Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a legendary
item and as such its Quality is 80 and it never alters.

## Run tests

`rspec`

## Examples

You can test your applicacion using `irb`

```ruby
require_relative './models.rb'

grs = GiledRoseSystem.new
#=> #<GiledRoseSystem:0x0000564a4d714d80 @items=[#<Item:0x0000564a4d714718 @name="+5 Dexterity Vest", @sell_in=10, @quality=20>, #<Item:0x0000564a4d7146f0 @name="Aged Brie", @sell_in=2, @quality=0>, #<Item:0x0000564a4d7146a0 @name="Elixir of the Mongoose", @sell_in=5, @quality=7>, #<Item:0x0000564a4d714650 @name="Sulfuras, Hand of Ragnaros", @sell_in=0, @quality=80>, #<Item:0x0000564a4d714628 @name="Backstage passes to a TAFKAL80ETC concert", @sell_in=15, @quality=20>, #<Item:0x0000564a4d714600 @name="Conjured Mana Cake", @sell_in=3, @quality=6>]>

items = grs.items
#=> [#<Item:0x0000564a4d714718 @name="+5 Dexterity Vest", @sell_in=10, @quality=20>, #<Item:0x0000564a4d7146f0 @name="Aged Brie", @sell_in=2, @quality=0>, #<Item:0x0000564a4d7146a0 @name="Elixir of the Mongoose", @sell_in=5, @quality=7>, #<Item:0x0000564a4d714650 @name="Sulfuras, Hand of Ragnaros", @sell_in=0, @quality=80>, #<Item:0x0000564a4d714628 @name="Backstage passes to a TAFKAL80ETC concert", @sell_in=15, @quality=20>, #<Item:0x0000564a4d714600 @name="Conjured Mana Cake", @sell_in=3, @quality=6>]

bc.end_day_update
```