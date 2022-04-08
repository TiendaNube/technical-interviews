# Bad Compression

You need to define a function that receives an string and returns the same string compressed using the "bad compression" algorithm.

## How does the "Bad Compression" algorithm works

If we have two consecutive characters we need to remove them from the string, we keep doing it till we can't do it anymore.

## Run tests

`rspec test_bad_compression.rb`

## Examples

You can test your applicacion using `irb`

```ruby
require_relative './bad_compression.rb'

bc = BadCompression.new
bc.bad_compression("a")
#=> "a"

bc.bad_compression("aa")
#=> ""

bc.bad_compression("aaa")
#=> "a"

bc.bad_compression("abcba")
#=> "abcba"
# (irreducible)

bc.bad_compression("abba")
#=> ""
# 1. we remove "bb", so the result is "aa". 
# 2. we remove "aa"
```