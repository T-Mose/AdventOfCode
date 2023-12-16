import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Dec7 {
    public static void main(String[] args) {
        long totalValue = 0;
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
        ArrayList<String> sortedStrings = new ArrayList<>();
        ArrayList<ArrayList<String>> hands = new ArrayList<>();
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        // Keeps track of all different types of hand strength in order
        for (String string : input) {
            String[] parts = string.split(" ");
            String hand = parts[0]; // The poker hand

            int[] handS = cardS(hand);
            char handType = getHandType(handS);
            switch (handType) {
                case '5':
                    hands.get(0).add(string);
                    System.out.println("here");
                    break;
                case '4':
                    hands.get(1).add(string);
                    break;
                case 'H':
                    hands.get(2).add(string);
                    break;
                case '3':
                    hands.get(3).add(string);
                    break;
                case '2':
                    hands.get(4).add(string);
                    break;
                case '1':
                    hands.get(5).add(string);
                    break;
                case 'C':
                    hands.get(6).add(string);
                    break;

                default:
                    break;
            }
        }
        // First sort a list based on their strength

        for (ArrayList<String> handList : hands) {
            if (handList.size() > 1) {
                System.out.println("Before sorting:");
                for (String string : handList) {
                    //System.out.println(string);
                }

                // Sort the hands between oneanother
                for (int i = 0; i < handList.size(); i++) {
                    for (int j = i + 1; j < handList.size(); j++) {
                        if (i != j) {
                            // num1 - 0
                            // num2 - 1
                            if (compare(handList.get(i), handList.get(j)) < 0) {
                                // Change place
                                                                //System.out.println("Changing: " + handList.get(i) + " with: " + handList.get(j));
                                //System.out.println("te");
                                                                String temp = handList.get(i);
                                handList.set(i, handList.get(j));
                                handList.set(j, temp);
                            }
                        }
                    }   
                }
                
                System.out.println("After sorting:");
                for (String string : handList) {
                    //System.out.println(string);
                }
            }
            sortedStrings.addAll(handList);
            System.out.println("temp");
        }
        // Then use the bid to calculate the hands value
        for (int i = 0; i < sortedStrings.size(); i++) {
            String current = sortedStrings.get(i);
            String[] parts = current.split(" ");
            String bid = parts[1]; // The poker hand
            totalValue += Integer.parseInt(bid) * (sortedStrings.size() - i);
            // System.out.println(totalValue + "=" + Long.parseLong(bid) + "*" + (sortedStrings.size() - i));
        }
        System.out.println(totalValue);
    }
    private static int compare(String first, String second) {
        for (int i = 0; i < 5; i++) {
            int firstVal = getVal(first.charAt(i));
            int secondVal = getVal(second.charAt(i));
    
            if (firstVal > secondVal) {
                return 1; // First hand is greater
            } else if (firstVal < secondVal) {
                return -1; // Second hand is greater
            }
        }
        return 0; // Hands are equal
    }

public static char getHandType(int[] hand) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    int jacksCount = 0;

    for (int card : hand) {
        if (card == 1) { // Assuming Jack is represented by 1
            jacksCount++;
        } else {
            frequencyMap.put(card, frequencyMap.getOrDefault(card, 0) + 1);
        }
    }

    // Use Jacks to complete or improve sets
    // This logic is just for part 2
    for (int i = 0; i < jacksCount; i++) {
        Optional<Integer> maxEntry = frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        if (maxEntry.isPresent()) {
            frequencyMap.put(maxEntry.get(), frequencyMap.get(maxEntry.get()) + 1);
        } else {
            // If no cards are present to improve, treat Jack as a standalone card
            frequencyMap.put(1, frequencyMap.getOrDefault(1, 0) + 1);
        }
    }

    int maxFrequency = Collections.max(frequencyMap.values());

    if (maxFrequency == 5) return '5'; // Five of a kind (with wildcards)
    if (maxFrequency == 4) return '4'; // Four of a kind
    if (maxFrequency == 3 && frequencyMap.size() == 2) return 'H'; // Full house
    if (maxFrequency == 3) return '3'; // Three of a kind
    if (maxFrequency == 2) {
        long pairCount = frequencyMap.values().stream().filter(freq -> freq == 2).count();
        return pairCount == 2 ? '2' : '1'; // Two pair or One pair
    }
    return 'C'; // High card
}


    static int[] cardS(String hand) {
        int[] handS = new int[5];
        int i = 0;
        for (char card : hand.toCharArray()) {
            // Each card
            handS[i] = getVal(card);
            i++;
        }
        return handS;
    }

    private static int getVal(char card) {
        if (card == 'A')
            return 14;
        if (card == 'T')
            return 10;
        if (card == 'J')
            return 1;
        if (card == 'Q')
            return 12;
        if (card == 'K')
            return 13;
        return Character.getNumericValue(card);
    }
}
