# String Pattern

You need to define a function that receives a string and a pattern and returns True if the string match the pattern or false otherwise.

## Examples

> Pattern="XY", String="abc" => True  (because of "ab" or "bc")

> Pattern="XXYY", String="abcder" => False

> Pattern="XXYY", String="abbccder" => True  (because of "bbcc")

> Pattern="XYZ", String="abbccder" => True  (because of "cde")

## Run tests

`py.test string_pattern_test.py`
