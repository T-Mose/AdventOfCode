import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dec1 {

    public static void main(String[] args) {
        int answer = 0;
        String firLett = "otfsen";
        String lastLett = "eorxnt";
        try {
            BufferedReader file = new BufferedReader(new FileReader("Input.txt"));

            String line = file.readLine();

            while (line != null) { // Kör tills texten tar slut
                String first = "";
                String last = "";
                int i = 0;
                int j = line.length() - 1;
                int debug = 0;
                while (true) {
                    if (first.length() == 0) {
                        if (Character.isDigit(line.charAt(i))) {
                            first = String.valueOf(line.charAt(i));
                        } else if (firLett.indexOf(line.charAt(i)) != -1) {
                            // ie it could be a number in spelling
                            char kar = line.charAt(i);
                            int before = i;
                            switch (kar) {
                                case 'o':
                                    if (line.length() > i + 3 && line.substring(i, i + 3).equals("one")) {
                                        first = "1";
                                        i += 3;
                                    }
                                    break;
                                case 't':
                                    if (line.length() > i + 3 && line.substring(i, i + 3).equals("two")) {
                                        first = "2";
                                        i += 3;
                                    } else if (line.length() > i + 5 && line.substring(i, i + 5).equals("three")) {
                                        first = "3";
                                        i += 5;
                                    }
                                    break;
                                case 'f':
                                    if (line.length() > i + 4 && line.substring(i, i + 4).equals("four")) {
                                        first = "4";
                                        i += 4;
                                    }
                                    else if (line.length() > i + 4 && line.substring(i, i + 4).equals("five")) {
                                        first = "5";
                                        i += 4;
                                    }
                                    break;
                                case 's':
                                    if (line.length() > i + 3 && line.substring(i, i + 3).equals("six")) {
                                        first = "6";
                                        i += 3;
                                    }
                                    else if (line.length() > i + 5 && line.substring(i, i + 5).equals("seven")) {
                                        first = "7";
                                        i += 5;
                                    }
                                    break;
                                case 'e':
                                    if (line.length() > i + 5 && line.substring(i, i + 5).equals("eight")) {
                                        first = "8";
                                        i += 5;
                                    }
                                    break;
                                case 'n':
                                    if (line.length() > i + 4 && line.substring(i, i + 4).equals("nine")) {
                                        first = "9";
                                        i += 4;
                                    }
                                    break;

                                default:
                                    System.out.println("ERROR!!");
                                    break;
                            }
                            if (i == before) {
                                i++;
                            }
                        } else
                            i++;
                    }

                    if (last.length() == 0) {
                        if (Character.isDigit(line.charAt(j))) {
                            last = String.valueOf(line.charAt(j));
                        } else if (lastLett.indexOf(line.charAt(j)) != -1) {
                            // Have possibly found the last letter in a number
                            char kar = line.charAt(j);
                            int before = j;
                            switch (kar) {
                                case 'e':
                                    if (j - 2 >= 0 && line.substring(j - 2, j + 1).equals("one")) {
                                        last = "1";
                                        j -= 3;
                                    }
                                    else if (j - 3 >= 0 && line.substring(j - 3, j + 1).equals("nine")) {
                                        last = "9";
                                        j -= 4;
                                    }
                                    else if (j - 4 >= 0 && line.substring(j - 4, j + 1).equals("three")) {
                                        last = "3";
                                        j -= 5;
                                    } else if (j - 3 >= 0 && line.substring(j - 3, j + 1).equals("five")) {
                                        last = "5";
                                        j -= 4;
                                    }

                                    break;
                                case 'o':
                                    if (j - 2 >= 0 && line.substring(j - 2, j + 1).equals("two")) {
                                        last = "2";
                                        j -= 3;
                                    }

                                    break;
                                case 'r':
                                    if (j - 3 >= 0 && line.substring(j - 3, j + 1).equals("four")) {
                                        last = "4";
                                        j -= 4;
                                    }

                                    break;
                                case 'x':
                                    if (j - 2 >= 0 && line.substring(j - 2, j + 1).equals("six")) {
                                        last = "6";
                                        j -= 3;
                                    }

                                    break;
                                case 'n':
                                    if (j - 4 >= 0 && line.substring(j - 4, j + 1).equals("seven")) {
                                        last = "7";
                                        j -= 5;
                                    }

                                    break;
                                case 't':
                                    if (j - 4 >= 0 && line.substring(j - 4, j + 1).equals("eight")) {
                                        last = "8";
                                        j -= 5;
                                    }
                                    break;

                                default:
                                    System.out.println("ERROR!!");
                                    break;
                            }
                            if (j == before)
                                j--;
                        } else
                            j--;
                    }

                    if (first.length() + last.length() == 2)
                    {
                        break;
                    }
                    // d2av2kfd9d
                    debug++;

                }
                answer += Integer.parseInt(first + last);
                line = file.readLine();
            }
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        }

        System.out.println(answer);
    }
}
