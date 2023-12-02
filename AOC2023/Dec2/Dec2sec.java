import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dec2sec {
    public static void main(String[] args) {
        int numCubes = 0;
        char colour = '0';
        int power = 0;
        int sum = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));

            String line = file.readLine();

            while (line != null) {
                String[] words = line.split("[ :]");
                int maxRed = 0;
                int maxBlue = 0;
                int maxGreen = 0;

                for (int i = 3; i < words.length; i++) {
                    while (i < words.length) {
                        if (i % 2 == 1) {
                            numCubes = Integer.parseInt(words[i]);
                        } else if (i % 2 == 0) {
                            colour = words[i].charAt(0);
                            switch (colour) {
                                case 'r':
                                    maxRed = numCubes > maxRed ? numCubes : maxRed;
                                    break;
                                case 'g':
                                    maxGreen = numCubes > maxGreen ? numCubes : maxGreen;
                                    break;
                                case 'b':
                                    maxBlue = numCubes > maxBlue ? numCubes : maxBlue;
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
                power = maxBlue * maxRed * maxGreen;
                sum += power;
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