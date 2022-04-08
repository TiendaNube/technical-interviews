from nose.tools import istest, eq_

from word_wrap import wrap


@istest
def no_wrap_on_empty_string():
    eq_('', wrap('', 1))


@istest
def no_wrap_when_string_shorter_than_limit():
    eq_('string', wrap('string', 10))


@istest
def split_one_word():
    eq_('wo\nrd', wrap('word', 2))


@istest
def split_one_word_many_times():
    eq_('abc\ndef\nghi\nj', wrap('abcdefghij', 3))


@istest
def wrap_two_words_after_space():
    eq_('word\nword', wrap('word word', 6))


@istest
def wrap_three_words_after_first_space():
    eq_('word\nword\nword', wrap('word word word', 6))


@istest
def wrap_three_words_after_second_space():
    eq_('word word\nword', wrap('word word word', 11))


@istest
def wrap_before_word_boundary():
    eq_('wor\nd\nwor\nd', wrap('word word', 3))


# Nice to have.

@istest
def wrap_three_words_after_second_space():
    eq_('word\ns hi\nword\ns\nword\ns', wrap('words hi words words', 4))
