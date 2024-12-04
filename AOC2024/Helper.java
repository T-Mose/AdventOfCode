import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    /**
     * Reads lines from a given file and returns them as a list of strings.
     *
     * @param filePath the path to the file
     * @return a list of strings containing each line of the file
     */
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(new FileReader(filePath))) {
            String line = file.readLine();
            while (line != null) {
                lines.add(line);
                line = file.readLine();
            }
        } catch (Exception e) {
            System.out.println("ERROR reading file: " + filePath + ", " + e.getMessage());
            System.exit(1);
        }
        return lines;
    }
}
