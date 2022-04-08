<?php

use PHPUnit\Framework\TestCase;

class ReverseWordsTest extends TestCase
{

    public function test_convertOneWord()
    {
        $this->assertEquals('aloh', ReverseWords::convert('hola'));
    }

    public function test_convertTwoWordsSeparatedByBlank()
    {
        $this->assertEquals('this is a normal sentence', ReverseWords::convert('siht si a lamron ecnetnes'));
    }

    public function test_convertWithSeparatorsAtTheBeginning()
    {
        $this->assertEquals('    0,32,xxx,98', ReverseWords::convert('    0,23,xxx,89'));
    }

    public function test_convertWithSeparatorsAtTheEnd()
    {
        $this->assertEquals('this,sentence,is,not;very;normal;;;;', ReverseWords::convert('siht,ecnetnes,si,ton;yrev;lamron;;;;'));
    }

    public function test_convertWithSeparatorsInTheMiddle()
    {
        $this->assertEquals('0,32;,,zyx,98', ReverseWords::convert('0,23;,,xyz,89'));
    }

    public function test_convertWhichIncludeOtherWords()
    {
        $this->assertEquals('aloh,odnumaloh', ReverseWords::convert('hola,holamundo'));
    }
}
