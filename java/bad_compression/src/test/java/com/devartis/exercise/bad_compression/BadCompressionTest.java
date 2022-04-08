package com.devartis.exercise.bad_compression;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BadCompressionTest {

    @Test
    public void test_empty() {
        assertEquals("", Utils.badCompress(""));
    }

    @Test
    public void test_a() {
        assertEquals("a", Utils.badCompress("a"));
    }

    @Test
    public void test_aba() {
        assertEquals("aba", Utils.badCompress("aba"));
    }

    @Test
    public void test_aa() {
        assertEquals("", Utils.badCompress("aa"));
    }

    @Test
    public void test_aab() {
        assertEquals("b", Utils.badCompress("aab"));
    }

    @Test
    public void test_aaaaa() {
        assertEquals("a", Utils.badCompress("aaaaa"));
    }

    @Test
    public void test_aabb() {
        assertEquals("", Utils.badCompress("aabb"));
    }

    @Test
    public void test_abba() {
        assertEquals("", Utils.badCompress("abba"));
    }

    @Test
    public void test_abaaba() {
        assertEquals("", Utils.badCompress("abaaba"));
    }

    @Test
    public void test_aaabccddd() {
        assertEquals("abd", Utils.badCompress("aaabccddd"));
    }
}