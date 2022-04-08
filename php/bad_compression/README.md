# Bad Compression

You need to define a function that receives a string and returns the same string compressed using the "bad compression" algorithm.

## How does the "Bad Compression" algorithm works

If we have two consecutive characters we need to remove them from the string, we keep doing it till we can't do it anymore.

## Setup env

In ubuntu 20.04, only dependencies required are:

`sudo apt install php php-xml php-mbstring`

## Run tests

`./vendor/bin/phpunit tests`