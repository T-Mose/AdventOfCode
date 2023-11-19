using System;

namespace AdventOfCode4
{
    class Program
    {
        static void Main(string[] args)
        {
            int i = 0;
            int value = 0;
            string line = "";
            string valOne;
            int valOneOne;
            int valOneTwo;
            int valTwoOne;
            int valTwoTwo;
            string valTwo;
            while (i++ < 1000)
            {
                line = Console.ReadLine();

                valOne = line.Remove(line.IndexOf(","));
                valTwo = line.Substring(line.IndexOf(",") + 1);

                valOneOne = int.Parse(valOne.Remove(valOne.IndexOf("-")));
                valOneTwo = int.Parse(valOne.Substring(valOne.IndexOf("-") + 1));

                valTwoOne = int.Parse(valTwo.Remove(valTwo.IndexOf("-")));
                valTwoTwo = int.Parse(valTwo.Substring(valTwo.IndexOf("-") + 1));

                if (valTwoOne >= valOneOne && valTwoTwo <= valOneTwo) // Fully contained
                {
                    value++;                            
                }
                else if (valOneOne >= valTwoOne && valOneTwo <= valTwoTwo) // Fully contained
                { 
                    value++;
                }
                else if (valOneTwo >= valTwoTwo && valOneOne <= valTwoTwo)
                {
                    value++;
                }
                else if (valTwoOne >= valOneOne && valTwoOne <= valOneTwo)
                {
                    value++;
                }
                else if (valOneOne >= valTwoOne && valOneOne <= valTwoTwo)
                {
                    value++;
                }
                else if (valOneTwo >= valTwoOne && valOneTwo <= valTwoTwo)
                {
                    value++;
                }

            }
            Console.WriteLine(value);
        }
    }
}
