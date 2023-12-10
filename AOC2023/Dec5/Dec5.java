import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Dec5 {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));
            String line = file.readLine();

            while (line != null) {
                input.add(line);
                line = file.readLine();
            }
            line = null;
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        } // Takes the entire input seperatly
        long answer = Long.MAX_VALUE;
        String seeds1 = "763445965 78570222 1693788857 146680070 1157620425 535920936 3187993807 180072493 1047354752 20193861 2130924847 274042257 20816377 596708258 950268560 11451287 3503767450 182465951 3760349291 265669041";
        // String seeds1 = "79 14 55 13"; // Testing

        String[] seeds2 = seeds1.split(" ");
        long[] seeds = new long[seeds2.length];
        for (int i = 0; i < seeds2.length; i++) {
            seeds[i] = Long.parseLong(seeds2[i]);
        }
        long[] tempSeeds = new long[seeds.length];
        for (int i = 0; i < tempSeeds.length; i++) {
            tempSeeds[i] = seeds[i];
        }

        for (String line : input) {
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(" ");
                long destination = Long.parseLong(parts[0]);
                long source = Long.parseLong(parts[1]);
                long range = Long.parseLong(parts[2]);

                for (int i = 0; i < seeds.length; i++) {
                    if (0 <= seeds[i] - source && source + range > seeds[i]) {
                        tempSeeds[i] = destination + (seeds[i] - source);
                    }
                }
            }
            if (!line.isEmpty() && Character.isLetter(line.charAt(0))) {
                System.arraycopy(tempSeeds, 0, seeds, 0, tempSeeds.length);
            } // Makes a deep copy
        }
        for (long inte : tempSeeds) {
            answer = inte < answer ? inte : answer;
        }
        System.out.println(answer);
    }
}