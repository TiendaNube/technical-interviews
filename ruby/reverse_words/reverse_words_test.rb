require 'test/unit'
require_relative 'reverse_words'
 
class ReverseWordsTest < Test::Unit::TestCase

	def test_reverse_one_word
	    assert_equal('aloh', reverse_words('hola'))
	end

	def test_reverse_words_separated_by_blank
	    assert_equal('this is a normal sentence', reverse_words('siht si a lamron ecnetnes'))
	end

	def test_reverse_words_with_separators_at_the_end
	    assert_equal('this,sentence,is,not;very;normal;;;;', reverse_words('siht,ecnetnes,si,ton;yrev;lamron;;;;'))
	end

	def test_reverse_words_with_separators_at_the_beginning
	    assert_equal('    0,32,xxx,98', reverse_words('    0,23,xxx,89'))
	end

	def test_reverse_words_with_more_than_one_separators_in_the_middle
	    assert_equal('0,32;,,xyz,98', reverse_words('0,23;,,zyx,89'))
	end

	def test_reverse_words_which_include_other_words
	    assert_equal('aloh,odnumaloh', reverse_words('hola,holamundo'))
	end
end
