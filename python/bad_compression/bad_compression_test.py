# coding=utf-8
from bad_compression import bad_compression


def test_empty():
    assert bad_compression("") == ""


def test_a():
    assert bad_compression("a") == "a"


def test_aba():
    assert bad_compression("aba") == "aba"


def test_aa():
    assert bad_compression("aa") == ""


def test_aab():
    assert bad_compression("aab") == "b"


def test_aaaaa():
    assert bad_compression("aaaaa") == "a"


def test_aabb():
    assert bad_compression("aabb") == ""


def test_abba():
    assert bad_compression("abba") == ""


def test_abaaba():
    assert bad_compression("abaaba") == ""


def test_aaabccddd():
    assert bad_compression("aaabccddd") == "abd"
