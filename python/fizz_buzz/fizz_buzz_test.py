from nose.tools import istest, eq_
from fizz_buzz import fizz_buzz

@istest
def should_return_the_first_3_elements():
    eq_([1, 2, 'Fizz'], fizz_buzz(3))

@istest
def should_return_the_first_6_elements():
    eq_([1, 2, 'Fizz', 4, 'Buzz', 'Fizz'], fizz_buzz(6))


@istest
def should_return_fizz_buzz_for_15():
    result = fizz_buzz(15)
    eq_(15, len(result))
    eq_('FizzBuzz', result[-1])


@istest
def should_return_150000_elements():
    result = fizz_buzz(150000)
    eq_(150000, len(result))
    eq_('FizzBuzz', result[-1])
