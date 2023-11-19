using System;
using System.Collections.Generic;

namespace AdventOfCode1
{
    class Program
    {
        static void Main(string[] args)
        {
            string line = "";
            int number = 0;
            // List<double> allNums = new List<double>();
            List<int> allNums = new List<int>();
            int i = 0;
            // int numberOfItems = 0; - used if the actual number of food items were needed, which they werent
            while (i++ < 2237)
            {
                line = Console.ReadLine();
                if (line != "")
                {
                    number += int.Parse(line);
                    // numberOfItems++;
                }
                else
                {
                    // string both = numberOfItems.ToString() + "," + number.ToString();
                    // allNums.Add(double.Parse(both));
                    allNums.Add(number);
                    number = 0;
                    // numberOfItems = 0;
                }
            }
            allNums.Sort();
            int current = 0;
            for (int j = 1; j <= 3; j++)
            {
                current += allNums[allNums.Count - j];
                Console.WriteLine(current);
            }
            /* string one = allNums[allNums.Count - 1].ToString();
            string two = allNums[allNums.Count - 2].ToString();
            string three = allNums[allNums.Count - 3].ToString();
            Console.Clear();
            Console.WriteLine(one);
            Console.WriteLine(two);
            Console.WriteLine(three);
            int final = 0;
            for (int j = 1; j <= 3; j++)
            {
                string temp = allNums[allNums.Count - j].ToString();
                string tempString = temp.Substring(temp.IndexOf(",") + 1);
                int num = int.Parse(tempString);
                Console.WriteLine(tempString);
                final += num;
            }
            Console.WriteLine(final);
            */
        }
    }
}
