var expect = require('chai').expect;
var reverseWords = require('./reverseWords');

describe('reverseWords', function () {

    it('Should reverse a single word', function () {
        expect("hola").to.be.equal(reverseWords("aloh"))
    });

    it('Should reverse words separated by blank', function () {
        expect("this is a normal sentence").to.be.equal(reverseWords("siht si a lamron ecnetnes"))
    });

    it('Should reverse words separated by one separator', function () {
        expect("this,sentence,is,not;very;normal;;;;").to.be.equal(reverseWords("siht,ecnetnes,si,ton;yrev;lamron;;;;"))
    });

    it('Should reverse words with separators at the end', function () {
        expect("0,32,xxx,98;;;;").to.be.equal(reverseWords("0,23,xxx,89;;;;"))
    });

    it('Should reverse words with separators at the beginning', function () {
        expect("    0,32,xyz,98").to.be.equal(reverseWords("    0,23,zyx,89"))
    });

    it('Should reverse words with more than one separators in the middle', function () {
        expect("0,32;,,65,98").to.be.equal(reverseWords("0,23;,,56,89"))
    });

    it('Should reverse words which include other words', function () {
        expect("aloh,odnumaloh").to.be.equal(reverseWords("hola,holamundo"))
    });

});