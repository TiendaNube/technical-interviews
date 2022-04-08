using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using static Microsoft.VisualStudio.TestTools.UnitTesting.CollectionAssert;
using static TreePaths.TreePath;

namespace TreePathsTest
{
    [TestClass]
    public class TreePathTest
    {
        [TestMethod]
        public void IgnoreEmpty()
        {
            AreEquivalent(new List<string> {"a/b/c"}, LargestPaths(new List<string> {"", "a/b/c", ""}));
        }

        [TestMethod]
        public void FilterDuplicates()
        {
            AreEquivalent(new List<string> {"a/b/c"}, LargestPaths(new List<string> {"a/b/c", "a/b/c"}));
        }

        [TestMethod]
        public void SimpleCase()
        {
            AreEquivalent(new List<string> {"a/b", "b"}, LargestPaths(new List<string> {"a", "a/b", "b"}));
        }

        [TestMethod]
        public void FilterPathsAlreadyCovered()
        {
            AreEquivalent(new List<string> {"a/b/c", "b", "d/e/f/g"},
                LargestPaths(new List<string>
                {
                    "a",
                    "a/b",
                    "a/b/c",
                    "a",
                    "a/b/c",
                    "b",
                    "d",
                    "d/e/f",
                    "d/e",
                    "d/e",
                    "",
                    "d/e/f/g"
                }));
        }
    }
}