using System;
using System.Collections.Generic;

namespace AdventOfCode5
{
    class Program
    {
        static void Main(string[] args)
        {
            // Nine diffrent stacks, anwnser will be a nine letter string
            string final = "";
            string line = "";
            List<string> stack1 = new List<string>() { "R", "P", "C", "D", "B", "G" };
            List<string> stack2 = new List<string>() { "G", "V", "H"};
            List<string> stack3 = new List<string>() { "M", "P", "J", "D", "Q", "S", "N" };
            List<string> stack4 = new List<string>() { "M", "N", "C", "D", "G", "L", "S", "P" };
            List<string> stack5 = new List<string>() { "S", "L", "F", "P", "C", "N", "B", "J" };
            List<string> stack6 = new List<string>() { "S", "T", "G", "V", "Z", "D", "B", "Q" };
            List<string> stack7 = new List<string>() { "Q", "T", "F", "H", "M", "Z", "B" };
            List<string> stack8 = new List<string>() { "F", "B", "D", "M", "C" };
            List<string> stack9 = new List<string>() { "G", "Q", "C", "F" };
            stack2.Reverse();
            stack3.Reverse();
            stack4.Reverse();
            stack5.Reverse();
            stack6.Reverse();
            stack7.Reverse();
            stack8.Reverse();
            stack9.Reverse();
            List<System.Collections.Generic.List<string>> allStacks = new List<System.Collections.Generic.List<string>>() {stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9};
            // All the diffrent stacks all collected --- If an error occurs check that these lists are correct

            int i = 10;
            int move;
            int from;
            int to;
            string temp;
            string toBeMoved = "";
            List<string> stackLosing = new List<string>();
            List<string> stackGainning = new List<string>();
            while (i++ < 512)
            {
                line = Console.ReadLine();
                temp = line.Remove(line.IndexOf("f"));
                move = int.Parse(temp.Substring(temp.IndexOf(" ")));

                temp = line.Substring(temp.Length);
                temp = temp.Remove(temp.IndexOf("t") - 1);
                from = int.Parse(temp.Substring(temp.IndexOf(" ")));

                to = int.Parse(line.Substring(line.IndexOf("t") + 3));
                // Converted the given information into acctions
                stackLosing = allStacks[from - 1];
                stackGainning = allStacks[to - 1];
                for (int j = 0; j < move; j++)
                {
                    toBeMoved += stackLosing[stackLosing.Count - 1];
                    // stackGainning.Add(stackLosing[stackLosing.Count - 1]); Change to not move each one at time
                    stackLosing.RemoveAt(stackLosing.Count - 1); // Remain the same
                }
                for (int g = move - 1; g >= 0; g--)
                {
                    stackGainning.Add(toBeMoved[g].ToString());
                }
                allStacks[from - 1] = stackLosing;
                allStacks[to - 1] = stackGainning;
                toBeMoved = "";
            }

            foreach (var item in allStacks)
            {
                final += item[item.Count - 1];
            }
            Console.WriteLine(final);
        }
    }
}
