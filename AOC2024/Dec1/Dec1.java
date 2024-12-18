
import java.util.ArrayList;
import java.util.List;

public class Dec1 {
    public static void main(String[] args) {
        // Get the entire input as a single list
        List<String> input = Helper.readFile("Dec1/Input.txt");

        // Setup for Dec1
        List<Integer> LeftSide = new ArrayList<>();
        List<Integer> RightSide = new ArrayList<>();
        int answerLength = 0;

        int i = 0;
        while (i < input.size()) {
            String[] line = input.get(i).split("\\s+");
            LeftSide.add(Integer.parseInt(line[0]));
            RightSide.add(Integer.parseInt(line[1]));
            i++;
        }
        LeftSide.sort(null);
        RightSide.sort(null);
        for (int j = 0; j < LeftSide.size(); j++) {
            answerLength += Math.abs(LeftSide.get(j) - RightSide.get(j));
        }
        System.out.println(answerLength);
    }
}