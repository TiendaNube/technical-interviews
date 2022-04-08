using System;
using BadCompression;

namespace ConsoleApplication
{
    class Programm
    {
        public static void Main(string[] args)
        {
            BadCompressionUtils bc = new BadCompressionUtils();
            Console.WriteLine(bc.BadCompression(""));
        }
    }
}
