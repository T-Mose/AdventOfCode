import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Dec8 {
    public static void main(String[] args) {
        // String action = "LR"; // Test 2
        String action = "LRRLRRRLRLRLLRRLRLRLLRLRLRLLLLRRRLLRRRLRRRLRRRLLRLLRLRRLRLRLRRRLLLLRRLRLRRLRRLLRRRLRRLRLRRLRRLRRLRRLRLLRRLRRLLLLRLRLRRLLRRLLRRLRLLRLRRLRRLRRLRRRLRRLLLRRLRRRLRLRRRLLRLRRLRRRLRRLLRRRLRRLRLLRRLLRRLRRLRRRLRRLLRRLRRRLRLRLRLRLRLRRLRRLLRRRLRLRRLRRRLRLRLRLRLRLRRRLRRLRRRLLRRLRLLRRRLRRLRLLLLRRRLRRLRRRR";
        char[] actions = action.toCharArray();
        ArrayList<String> allAs = new ArrayList<>();
        ArrayList<Integer> answers = new ArrayList<>();

        HashMap<String, String[]> input = new HashMap<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));
            String line = file.readLine();
            while (line != null) {
                String[] parts = line.replace("(", "").replace(")", "").split(" = |, ");
                input.put(parts[0], new String[] { parts[1], parts[2] });
                if (parts[0].charAt(2) == ('A')) {
                    allAs.add(parts[0]);
                }
                line = file.readLine();
            }
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        } // Takes in the file input seperatly

        for (String string : allAs) {
            String current = string;
            int steps = 0;
            while (current.charAt(2) != 'Z') {
                char currAction = actions[steps % action.length()];
                if (currAction == 'R') {
                    current = input.get(current)[1];
                } else if (currAction == 'L') {
                    current = input.get(current)[0];
                }
                steps++;
            }
            answers.add(steps);
        }
        // Now find the lcm of the list answers
        long lcm12 = lcm(answers.get(0), answers.get(1));
        long lcm34 = lcm(answers.get(2), answers.get(3));
        long lcm56 = lcm(answers.get(4), answers.get(5));

        long lcm1234 = lcm(lcm12,lcm34);
        long lcm123456 = lcm(lcm1234, lcm56);

        System.out.println(lcm123456);
    }
    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    // Method to calculate GCD using Euclidean algorithm
    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
