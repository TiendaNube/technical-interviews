package com.devartis.exercise.reverse_words;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseWordsTest {

    @Test
    public void reverseOneWord() {
        assertEquals("aloh", Utils.reverseWords("hola"));
    }

    @Test
    public void reverseWordsSeparatedByBlank() {
        assertEquals("this is a normal sentence", Utils.reverseWords("siht si a lamron ecnetnes"));
    }

    @Test
    public void reverseWordsWithSeparatorsAtTheEnd() {
        assertEquals("this,sentence,is,not;very;normal;;;;", Utils.reverseWords("siht,ecnetnes,si,ton;yrev;lamron;;;;"));
    }

    @Test
    public void reverseWordsWithSeparatorsAtTheBeginning() {
        assertEquals("    0,32,xxx,98", Utils.reverseWords("    0,23,xxx,89"));
    }

    @Test
    public void reverseWordsWithSeparatorsInTheMiddle() {
        assertEquals("0,32;,,xyz,98", Utils.reverseWords("0,23;,,zyx,89"));
    }

    @Test
    public void reverseWordsWhichIncludeOtherWords() {
        assertEquals("aloh,odnumaloh", Utils.reverseWords("hola,holamundo"));
    }

}