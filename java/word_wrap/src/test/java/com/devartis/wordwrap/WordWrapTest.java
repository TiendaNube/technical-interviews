package com.devartis.wordwrap;

import org.junit.Test;

import static com.devartis.wordwrap.WordWrapper.wrap;
import static org.junit.Assert.assertEquals;


public class WordWrapTest {

    @Test
    public void noWrapOnEmptyString() {
        assertEquals("", wrap("", 1));
    }

    @Test
    public void noWrapWhenStringShorterThanLimit() {
        assertEquals("string", wrap("string", 10));
    }

    @Test
    public void wrapTwoWordsAfterSpace() {
        assertEquals("word\nword", wrap("word word", 6));
    }

    @Test
    public void wrapThreeWordsAfterFirstSpace() {
        assertEquals("word\nword\nword", wrap("word word word", 6));
    }

    @Test
    public void wrapThreeWordsAfterSecondSpace() {
        assertEquals("word word\nword", wrap("word word word", 11));
    }

    @Test
    public void splitOneWord() {
        assertEquals("wo\nrd", wrap("word", 2));
    }

    @Test
    public void splitOneWordManyTimes() {
        assertEquals("abc\ndef\nghi\nj", wrap("abcdefghij", 3));
    }

    @Test
    public void wrapBeforeWordBoundary() {
        assertEquals("wor\nd\nwor\nd", wrap("word word", 3));
    }
    
    @Test
    public void includeWordAfterBreak() {
        assertEquals("word\ns hi\nword\ns\nword\ns", wrap("words hi words words", 4));
    }
}