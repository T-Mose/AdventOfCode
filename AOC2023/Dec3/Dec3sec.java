import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec3sec {
    public static String intFor(String line, int loc) {
        String tempNum = "";
        while (loc + 1 < line.length() && Character.isDigit(line.charAt(loc + 1))) {
            tempNum += line.charAt(loc + 1);
            loc++;
        }
        return tempNum; // Returns the extended number
    }

    public static String intBack(String line, int loc) {
        String tempNum = "";
        StringBuilder reverseTempNum = new StringBuilder();
        while (loc - 1 >= 0 && Character.isDigit(line.charAt(loc - 1))) {
            reverseTempNum.append(line.charAt(loc - 1));
            loc--;
        }
        tempNum = reverseTempNum.reverse().toString();
        return tempNum; // Returns the extended found number and rev since back
    }

    public static void checkOtherRow(String currentLine, String otherLine, int asteriskIndex) {
        int start = -1;

        for (int i = 0; i < otherLine.length(); i++) {
            if (Character.isDigit(otherLine.charAt(i))) {
                if (start == -1) {
                    start = i; // Mark the start of a number
                }
                if (i == otherLine.length() - 1 || !Character.isDigit(otherLine.charAt(i + 1))) {
                    // End of a number
                    if (start <= asteriskIndex + 1 && i >= asteriskIndex - 1) {
                        adjNum.add(otherLine.substring(start, i + 1));
                    } // Checks that the number have some overlapp with the asterisk + diagonal
                    start = -1; // Reset start for the next number
                }
            }
        }
    }

    public static ArrayList<String> adjNum;

    public static void main(String[] args) {
        int sum = 0;
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
        }

        // Now have an array list with all the components
        for (int j = 0; j < input.size(); j++) {
            String line = input.get(j);
            String tLine; // temp line for the above and below
            for (int i = 0; i < line.length(); i++) {
                adjNum = new ArrayList<>(); // For each possible asterisk we keep track of its adjacnt numbers
                if (line.charAt(i) == '*') {
                    // Have found an asterisk

                    if (i + 1 != line.length() && Character.isDigit(line.charAt(i + 1))) {
                        adjNum.add(intFor(line, i));
                    }
                    if (i > 0 && Character.isDigit(line.charAt(i - 1))) {
                        adjNum.add(intBack(line, i));
                    }
                    // Checks the same row
                    if (j != 0) {
                        // Line above
                        tLine = input.get(j - 1);
                        checkOtherRow(line, tLine, i);
                    }
                    if (j + 1 != input.size()) {
                        // Line below
                        tLine = input.get(j + 1);
                        checkOtherRow(line, tLine, i);
                    }

                    if (adjNum.size() == 2) {
                        // Since exactly 2 are required
                        sum += Integer.parseInt(adjNum.get(0)) * Integer.parseInt(adjNum.get(1));
                    }
                }
            }
        }
        System.out.println(sum); // Answer
    }
}
