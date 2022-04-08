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

`python gilded_rose_test.py`

## Examples

You can test your applicacion using `pipenv run python`

```python

from gilded_rose import GildedRoseSystem

grs = GiledRoseSystem()

grs.items
#[<gilded_rose.Item object at 0x7f1c46dde470>, <gilded_rose.Item object at 0x7f1c46dde5f8>, <gilded_rose.Item object at 0x7f1c46dde6a0>, <gilded_rose.Item object at 0x7f1c46dde588>, <gilded_rose.Item object at 0x7f1c46dde5c0>, <gilded_rose.Item object at 0x7f1c46dde668>

grs.end_day_update()
```