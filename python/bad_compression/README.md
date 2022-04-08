# Bad Compression

You need to define a function that receives an string and returns the same string compressed using the "bad compression" algorithm.

## How does the "Bad Compression" algorithm works

If we have two consecutive characters we need to remove them from the string, we keep doing it till we can't do it anymore.

## Run tests

`py.test bad_compression_test.py`

## Examples

```python
from bad_compression import bad_compression

bad_compression("a")
#=> "a"

bad_compression("aa")
#=> ""

bad_compression("aaa")
#=> "a"

bad_compression("abcba")
#=> "abcba"
# (irreducible)

bad_compression("abba")
#=> ""
# 1. we remove bb, so the result is aa. 2. we remove aa
```
