<?php

use PHPUnit\Framework\TestCase;

class BadCompressionTest extends TestCase
{

    public function test_empty()
    {
        $this->assertEquals("", BadCompression::compress(""));
    }

    public function test_a()
    {
        $this->assertEquals("a", BadCompression::compress("a"));
    }

    public function test_aba()
    {
        $this->assertEquals("aba", BadCompression::compress("aba"));
    }

    public function test_aa()
    {
        $this->assertEquals("", BadCompression::compress("aa"));
    }

    public function test_aab()
    {
        $this->assertEquals("b", BadCompression::compress("aab"));
    }

    public function test_aaaaa()
    {
        $this->assertEquals("a", BadCompression::compress("aaaaa"));
    }

    public function test_aabb()
    {
        $this->assertEquals("", BadCompression::compress("aabb"));
    }

    public function test_abba()
    {
        $this->assertEquals("", BadCompression::compress("abba"));
    }

    public function test_abaaba()
    {
        $this->assertEquals("", BadCompression::compress("abaaba"));
    }

    public function test_aaabccddd()
    {
        $this->assertEquals("abd", BadCompression::compress("aaabccddd"));
    }
}