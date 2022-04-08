# coding=utf-8
from string_pattern import match_pattern


def test_empty():
    assert match_pattern("abcd", "")


def test_xy():
    assert match_pattern("abcd", "XY")
    assert not match_pattern("aaaa", "XY")


def test_xxy():
    assert match_pattern("aabaa", "XXY")
    assert not match_pattern("abce", "XXY")


def test_xxyy():
    assert match_pattern("aacddeef", "XXYY")
    assert not match_pattern("aacdeef", "XXYY")


def test_xyz():
    assert match_pattern("aacddeef", "XYZ")
    assert not match_pattern("aaccddeef", "XYZ")
