import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dec4sec {
    public static void main(String[] args) {
        HashMap<Integer, String> cards = new HashMap<>();
        ArrayList<String> allCards = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));
            String line = file.readLine();
            while (line != null) {
                String[] parts = line.split(":");
                int key = Integer.parseInt(parts[0].substring(4).trim());
                cards.put(key, line);
                allCards.add(line);
                line = file.readLine();
            }
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        }
        // Initialized the hashmap, and arrayList
        for (int i = 0; i < allCards.size(); i++) {
            int add = 0;
            String[] parts = allCards.get(i).split(":|\\|");
            int key = Integer.parseInt(parts[0].substring(4).trim());
            String[] correctVals = parts[1].trim().split("\\s+");
            String[] myVals = parts[2].trim().split("\\s+");
            for (int j = 0; j < correctVals.length; j++) {
                if (Arrays.asList(myVals).contains(correctVals[j])) {
                    allCards.add(cards.get(key + ++add));
                }
            }
        }

        System.out.println(allCards.size());
    }
}