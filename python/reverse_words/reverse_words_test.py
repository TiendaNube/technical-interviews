from nose.tools import istest, eq_
from reverse_words import reverse_words

@istest
def should_reverse_one_word():
    eq_('aloh', reverse_words('hola'))


@istest
def should_reverse_two_words_separated_by_blank():
    eq_('this is a normal sentence', reverse_words('siht si a lamron ecnetnes'))


@istest
def should_reverse_words_with_separators_at_the_end():
    eq_('this,sentence,is,not;very;normal;;;;', reverse_words('siht,ecnetnes,si,ton;yrev;lamron;;;;'))


@istest
def should_reverse_words_with_separators_at_the_beginning():
    eq_('    0,32,xxx,98', reverse_words('    0,23,xxx,89'))


@istest
def should_reverse_words_with_separators_in_the_middle():
    eq_('0,32;,,zyx,98', reverse_words('0,23;,,xyz,89'))


@istest
def should_reverse_words_which_include_other_words():
	eq_('aloh,odnumaloh', reverse_words('hola,holamundo'))
