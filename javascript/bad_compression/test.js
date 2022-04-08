var expect = require('chai').expect;
var badCompress = require('./badCompression');

describe('badCompress', function () {

    it('test_empty', function () {
        expect("").to.be.equal(badCompress(""))
    });

    it('test_a', function () {
        expect("a").to.be.equal(badCompress("a"))
    });

    it('test_aba', function () {
        expect("aba").to.be.equal(badCompress("aba"))
    });

    it('test_aa', function () {
        expect("").to.be.equal(badCompress("aa"))
    });

    it('test_aab', function () {
        expect("b").to.be.equal(badCompress("aab"))
    });

    it('test_aaaaa', function () {
        expect("a").to.be.equal(badCompress("aaaaa"))
    });

    it('test_aabb', function () {
        expect("").to.be.equal(badCompress("aabb"))
    });

    it('test_abba', function () {
        expect("").to.be.equal(badCompress("abba"))
    });

    it('test_abaaba', function () {
        expect("").to.be.equal(badCompress("abaaba"))
    });

    it('test_aaabccddd', function () {
        expect("abd").to.be.equal(badCompress("aaabccddd"))
    });

});