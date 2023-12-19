import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Dec9 {
    // https://adventofcode.com/2023/day/9
    public static void main(String[] args) {
        int answer = 0;
        ArrayList<String> input = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));
            String line = file.readLine();
            while (line != null) {
                input.add(line);
                line = file.readLine();
            }
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        } // Takes in the file input seperatly

        for (String string : input) {
            ArrayList<Integer> first = new ArrayList<>();
            boolean allZeros = false;
            int[] line = Arrays.stream(string.split("\\s+")) // Splitting the string by spaces
                    .mapToInt(Integer::parseInt) // Converting each part to an integer
                    .toArray();

            while (!allZeros) {
                first.add(line[0]);
                int[] diff = new int[line.length - 1];
                for (int i = 0; i < diff.length; i++) {
                    diff[i] = line[i + 1] - line[i];
                }
                line = diff;
                allZeros = Arrays.stream(diff).allMatch(v -> v == 0);
            }
            first.add(0);

            int tempNum = 0;
            for (int i = first.size() - 1; i > 0; i--) {
                tempNum = first.get(i - 1) - first.get(i);
                System.out.println(tempNum + "=" + first.get(i - 1) + "-" + first.get(i));
                first.set(i - 1, tempNum);
            }
                                        answer += tempNum;
        }
        System.out.println(answer);
    }
}
