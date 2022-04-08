# Sieve of Eratosthenes

A prime number is a natural number that has exactly two distinct natural number divisors: 1 and itself.

To find all the prime numbers less than or equal to a given integer n by Eratosthenes' method:

1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
1. Initially, let p equal 2, the smallest prime number.
1. Enumerate the multiples of p by counting to n from 2p in increments of p, and mark them in the list (these will be 2p, 3p, 4p, ...; the p itself should not be marked).
1. Find the first number greater than p in the list that is not marked. If there was no such number, stop. Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.
1. When the algorithm terminates, the numbers remaining not marked in the list are all the primes below n.

The main idea here is that every value given to p will be prime, because if it were composite it would be marked as a multiple of some other, smaller prime. Note that some of the numbers may be marked more than once (e.g., 15 will be marked both for 3 and 5).

As a refinement, it is to initially list odd numbers only, (3, 5, ..., n), and count in increments of 2p from 2p in step 3, thus marking only odd multiples of p. This actually appears in the original algorithm. This can be generalized with wheel factorization, forming the initial list only from numbers coprime with the first few primes and not just from odds (i.e., numbers coprime with 2), and counting in the correspondingly adjusted increments so that only such multiples of p are generated that are coprime with those small primes, in the first place.

## Example

To find all the prime numbers less than or equal to 30, proceed as follows.

First, generate a list of integers from 2 to 30:

` 2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30`

The first number in the list is 2; cross out every 2nd number in the
list after 2 by counting up from 2 in increments of 2 (these will be all
the multiples of 2 in the list):

` 2  3  x  5  x  7  x  9  xx 11 xx 13 xx 15 xx 17 xx 19 xx 21 xx 23 xx 25 xx 27 xx 29 xx`

The next number in the list after 2 is 3; cross out every 3rd number in
the list after 3 by counting up from 3 in increments of 3 (these will be
all the multiples of 3 in the list):

` 2  3  x  5  x  7  x  x  xx 11 xx 13 xx xx xx 17 xx 19 xx xx xx 23 xx 25 xx xx xx 29 xx`

The next number not yet crossed out in the list after 3 is 5; cross out
every 5th number in the list after 5 by counting up from 5 in increments
of 5 (i.e. all the multiples of 5):

` 2  3  x  5  x  7  x  x  xx 11 xx 13 xx xx xx 17 xx 19 xx xx xx 23 xx xx xx xx xx 29 xx`

The next number not yet crossed out in the list after 5 is 7; the next
step would be to cross out every 7th number in the list after 7, but
they are all already crossed out at this point, as these numbers (14,
21, 28) are also multiples of smaller primes because 7 × 7 is greater
than 30. The numbers not crossed out at this point in the list are all
the prime numbers below 30:

` 2  3     5     7           11    13          17    19          23                29`

## Run tests

`nosetests sieve_tests.py`
