import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Dec4 {
    public static void main(String[] args) {
        int sum = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));

            String line = file.readLine();

            while (line != null) {
                String[] parts = line.split(":|\\|");
                String[] correctVals = parts[1].trim().split("\\s+");
                String[] myVals = parts[2].trim().split("\\s+");
                int num = 0;
                for (int i = 0; i < correctVals.length; i++) {
                    if (Arrays.asList(myVals).contains(correctVals[i])) {
                        if (num == 0)
                        num++;
                        else
                        num*=2;
                    }
                }
                sum += num;
                line = file.readLine();
            }
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        }
        System.out.println(sum);
    }
}