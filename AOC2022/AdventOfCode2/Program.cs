using System;

namespace AdventOfCode2
{
    class Program
    {
        static void Main(string[] args)
        {
            // A = Rock (opp), 1
            // B = Paper (opp), 2
            // C = Scissor (opp), 3
            // X = Lose, Y = Draw, Z = Win
            int i = 0;
            int score = 0;
            string myAct;
            string oppAct;
            string line;
            while (i++ < 2500)
            {
                line = Console.ReadLine();
                oppAct = line.Remove(line.IndexOf(" "));
                myAct = line.Substring(line.IndexOf(" ") + 1);
                score += Calc(oppAct, myAct);
                Console.WriteLine(score);
            }
            Console.WriteLine(score);
        }
        static int Calc(string oppAct, string myAct)
        {
            int value = 0;
            switch (myAct) // Adds the default value per action
            {
                case "X":
                    // value += 0; Since X i lose
                    if (oppAct == "A")
                    {
                        value += 3;
                    }
                    else if (oppAct == "B")
                    {
                        value += 1;
                    }
                    else if (oppAct == "C")
                    {
                        value += 2;
                    }
                    break;
                case "Y":
                    value += 3; // Draw
                    if (oppAct == "A")
                    {
                        value += 1;
                    }
                    else if (oppAct == "B")
                    {
                        value += 2;
                    }
                    else if (oppAct == "C")
                    {
                        value += 3;
                    }
                    break;
                case "Z":
                    value += 6; // Win
                    if (oppAct == "A")
                    {
                        value += 2;
                    }
                    else if (oppAct == "B")
                    {
                        value += 3;
                    }
                    else if (oppAct == "C")
                    {
                        value += 1;
                    }
                    break;
            }
            return value;
        }
    }
}
