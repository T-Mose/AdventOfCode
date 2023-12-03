import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dec3 {
    public static boolean isSymbol(char ch) {
        return !Character.isDigit(ch) && ch != '.';
    }

    public static void main(String[] args) {
        int sum = 0;
        String tempNum;
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
        for (int j = 0; j < input.size(); j++) {
            String line = input.get(j);
            for (int i = 0; i < line.length(); i++) {
                boolean valid = false;
                int fir = 0;
                tempNum = "";
                String tLine;

                if (Character.isDigit(line.charAt(i)))
                    fir = i;
                while (i < line.length() && Character.isDigit(line.charAt(i))) {
                    tempNum += String.valueOf(line.charAt(i));
                    i++;
                }
                // We now have a number, lets check if its adjacent
                // Make checks
                // i right now is the possition after the last number
                if (i != line.length() && isSymbol(line.charAt(i)))
                    valid = true;
                if (fir != 0 && isSymbol(line.charAt(fir - 1)))
                    valid = true;
                // These check the same line

                int back = 0;
                int forward = 0;
                if (fir != 0)
                    back++;
                if (i != line.length())
                    forward++;

                if (j != 0) {
                    // We check the line before
                                    tLine = input.get(j - 1);
                    for (int a = fir - back; a < i + forward; a++) {
                        if (isSymbol(tLine.charAt(a))) {
                            valid = true;
                        }
                    }
                }
                if (j + 1 != input.size()) {
                    // The line after
                                    tLine = input.get(j + 1);
                    for (int a = fir - back; a < i + forward; a++) {
                        if (isSymbol(tLine.charAt(a))) {
                            valid = true;
                        }
                    }
                }

                if (valid && tempNum.length() != 0)
                    sum += Integer.parseInt(tempNum);

            }
        }
        System.out.println(sum);
    }
}
