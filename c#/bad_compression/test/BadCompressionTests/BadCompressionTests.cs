using System;
using Xunit;
using BadCompression;

namespace BadCompressionTests
{
    public class BadCompressionTests
    {
        BadCompressionUtils bc;
        public BadCompressionTests()
        {
            bc = new BadCompressionUtils();
        }

        [Theory]
        [InlineData("", "")]
        [InlineData("a", "a")]
        [InlineData("aba", "aba")]
        [InlineData("aa", "")]
        [InlineData("aab", "b")]
        [InlineData("aaaaa", "a")]
        [InlineData("aabb", "")]
        [InlineData("abba", "")]
        [InlineData("abaaba", "")]
        [InlineData("aaabccddd", "abd")]
        public void TestSampleString(string sample, string expected)
        {
            Assert.Equal(expected, bc.BadCompression(sample));
        }
    }
}
