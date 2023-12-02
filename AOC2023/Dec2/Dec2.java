import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dec2 {
    static final int MAX_RED = 12;
    static final int MAX_GREEN = 13;
    static final int MAX_BLUE = 14;

    public static void main(String[] args) {
        int idSum = 0;
        int gameID;
        boolean valid = true;
        int cubes = 0;
        char colour = '0';

        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));

            String line = file.readLine();

            while (line != null) {
                String[] words = line.split("[ :]");
                gameID = Integer.parseInt(words[1]);

                for (int i = 3; i < words.length; i++) {
                    while (i < words.length) {
                        if (i % 2 == 1) {
                            cubes = Integer.parseInt(words[i]);
                        } else if (i % 2 == 0 && valid) {
                            colour = words[i].charAt(0);
                            switch (colour) {
                                case 'r':
                                    valid = cubes <= MAX_RED;
                                    break;
                                case 'g':
                                    valid = cubes <= MAX_GREEN;
                                    break;
                                case 'b':
                                    valid = cubes <= MAX_BLUE;
                                    break;
                                default:
                                    System.out.println("ERRRIIRR!");
                                    break;
                            }
                        }

                        if (words[i].contains(Character.toString(';')))
                            break;
                        i++;
                    }
                }

                if (valid) {
                    System.out.println(gameID);
                    idSum += gameID;
                }
                valid = true;
                line = file.readLine();
            }
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        }
        System.out.println(idSum);
    }
}
