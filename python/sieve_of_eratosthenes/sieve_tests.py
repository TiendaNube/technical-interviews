from nose.tools import istest, eq_

from sieve import sieve


@istest
def find_first_four_prime_numbers():
    eq_([2, 3, 5, 7], sieve(10))


@istest
def find_first_prime_number():
    eq_([2], sieve(2))


@istest
def one_is_not_prime():
    eq_([], sieve(1))


@istest
def does_not_fail_with_negative_input():
    eq_([], sieve(-1))


@istest
def does_not_fail_with_negative_input():
    eq_([], sieve(-1))


@istest
def first_100_008_primes():
    '''
    See https://primes.utm.edu/lists/small/100000.txt
    The 100,008th is 1,299,827
    This should take a few seconds (in a fast computer)
    '''
    primes = sieve(1299827)
    eq_(541, primes[99])
    eq_(7_919, primes[999])
    eq_(104_729, primes[9_999])
    eq_(1_299_709, primes[99_999])
    eq_(1_299_827, primes[-1])
    eq_(100_008, len(primes))
    eq_([2, 3, 5, 7, 11, 13, 17, 19, 23, 29], primes[:10])
    eq_([101, 103, 107, 109, 113, 127, 131, 137, 139, 149], primes[25:35])
