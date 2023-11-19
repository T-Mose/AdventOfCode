using System;

namespace AdventOfCode7
{
    class Program
    {
        static void Main(string[] args)
        {
            int value = 0;
            string line = "";
            int[,] grid = new int[99, 99];
            int i = 0;
            int g = 0;
            while (i < 99)
            {
                line = Console.ReadLine();
                foreach (char item in line)
                {
                    grid[i, g] = item;
                    g++;
                }
                i++;
            }

            Console.ReadLine();
            Console.Clear();

            for (int b = 0; b < 99; g++)
            {
                for (int a = 0; a < 99; a++)
                {
                    Console.Write(grid[b, a]);
                }
                Console.WriteLine();
            }
        }
    }
}
