using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ReverseWords;

namespace ReverseWordsTests
{
    [TestClass]
    public class ReverseWordsTest
    {
        [TestMethod]
        public void TestReverseOneWord()
        {
            Assert.AreEqual("aloh", Utils.reverseWords("hola"));
        }

        [TestMethod]
        public void TestReverseWordsSeparatedByBlank()
        {
            Assert.AreEqual("this is a normal sentence", Utils.reverseWords("siht si a lamron ecnetnes"));
        }

        [TestMethod]
        public void TestReverseWordsWithSeparatorsAtTheEnd()
        {
            Assert.AreEqual("this,sentence,is,not;very;normal;;;;", Utils.reverseWords("siht,ecnetnes,si,ton;yrev;lamron;;;;"));
        }

        [TestMethod]
        public void TestReverseWordsWithSeparatorsAtTheBegining()
        {
            Assert.AreEqual("    0,32,xxx,98", Utils.reverseWords("    0,23,xxx,89"));
        }

        [TestMethod]
        public void TestReverseWordsWithMoreThanOneSeparatorInTheMiddle()
        {
            Assert.AreEqual("0,32;,,xyz,98", Utils.reverseWords("0,23;,,zyx,89"));
        }

        [TestMethod]
        public void TestReverseWordsWhichIncludeOtherWords()
        {
            Assert.AreEqual("aloh,odnumaloh", Utils.reverseWords("hola,holamundo"));
        }

    }
}
