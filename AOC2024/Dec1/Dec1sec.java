import java.util.*;

public class Dec1sec {
    public static void main(String[] args) {
        // Get the entire input as a single list
        List<String> input = Helper.readFile("Dec1/Input.txt");

        // Setup for Dec1
        List<Integer> LeftSide = new ArrayList<>();
        List<Integer> RightSide = new ArrayList<>();
        int answerSimilarityScore = 0;

        int i = 0;
        while (i < input.size()) {
            String[] line = input.get(i).split("\\s+");
            LeftSide.add(Integer.parseInt(line[0]));
            RightSide.add(Integer.parseInt(line[1]));
            i++;
        }

        for (int j = 0; j < LeftSide.size(); j++) {
            Integer currentNum = LeftSide.get(j);
            answerSimilarityScore += currentNum * Collections.frequency(RightSide, currentNum);
        }
        System.out.println(answerSimilarityScore);
    }
}
