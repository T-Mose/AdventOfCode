import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// Solves it effectivly by seeing all the numbers as ranges
// These are checked fromt the perspective of the range, not the possible new value
// This significantly reduces number of checks
// Then all the work is just checking bound of the ranges and remapping the correct partial
// bound and creating a new range for the remainder. The total range should always be the same
// or practically the same, since new mapped ranges could overlapp, to only less efficiency
// @author T3D , @date 2023-12-05
public class Dec5sec {
    public static void main(String[] args) {
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

        String seeds1 = "763445965 78570222 1693788857 146680070 1157620425 535920936 3187993807 180072493 1047354752 20193861 2130924847 274042257 20816377 596708258 950268560 11451287 3503767450 182465951 3760349291 265669041";
        // String seeds1 = "79 14 55 13"; // Testing above is the correct input og seed string

        // Parse the input string into pairs
        String[] seedPairs = seeds1.split(" ");
        List<SeedRange> seedRanges = new ArrayList<>();
        List<SeedRange> tempSeedRanges = new ArrayList<>(); // So new mappings dont get remapped on the same category

        for (int i = 0; i < seedPairs.length; i += 2) {
            long begin = Long.parseLong(seedPairs[i]);
            long range = Long.parseLong(seedPairs[i + 1]);
            seedRanges.add(new SeedRange(begin, begin + range - 1, "Start"));
        }

        for (String line : input) {
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(" ");
                long destination = Long.parseLong(parts[0]);
                long source = Long.parseLong(parts[1]);
                long range = Long.parseLong(parts[2]);
                for (int i = 0; i < seedRanges.size(); i++) {
                    SeedRange currentRange = seedRanges.get(i);
                    char overlapp = currentRange.getOverlapp(source, source + range - 1); // What type of overlapp there is
                    if (overlapp != '0') {
                        long overlappBegin = currentRange.getStartOverlapp(source);
                        long overlappEnd = currentRange.getEndOverlapp(source + range - 1);
                        long delta = source - destination; // What the new mapping should differ
                        switch (overlapp) {
                            case 'E': // Complete overlapp
                                tempSeedRanges.add(new SeedRange(currentRange.getStart() - delta,
                                        currentRange.getEnd() - delta, "complete"));
                                seedRanges.remove(currentRange);
                                i--; // Account for removal
                                break;
                            case 'M': // Middle overlapp
                                SeedRange left = new SeedRange(currentRange.getStart(), overlappBegin,
                                        "left contained");
                                SeedRange middleNew = new SeedRange(destination, destination + range - 1,
                                        "left contained");
                                SeedRange right = new SeedRange(overlappEnd, currentRange.getEnd(), "right contained");
                                // left and right, are the new values for the original range, with a new mapped hole
                                tempSeedRanges.add(middleNew);
                                seedRanges.set(i, left);
                                seedRanges.add(i + 1, right);
                                i++; // Since added to current mapping
                                break;
                            case 'L':
                                SeedRange newLeft = new SeedRange(currentRange.getStart() - delta,
                                        destination + range - 1, "one-side left");
                                tempSeedRanges.add(newLeft);
                                seedRanges.set(i,
                                        new SeedRange(overlappEnd + 1, currentRange.getEnd(), "New one-sided left"));
                                break;
                            case 'R':
                                SeedRange newRight = new SeedRange(destination, currentRange.getEnd() - delta,
                                        "one-side right");
                                tempSeedRanges.add(newRight);
                                seedRanges.set(i, new SeedRange(currentRange.getStart(), overlappBegin - 1,
                                        "New one-sided right"));
                                break;
                            default: // Should never be reached
                                System.out.println(overlapp + " somehing went wrong!");
                                break;
                        }
                    }
                }
            }
            if (!line.isEmpty() && Character.isLetter(line.charAt(0))) { // New category
                seedRanges.addAll(tempSeedRanges); // Merge the new mappings to the old
                tempSeedRanges.clear(); // Reset new mappings
            }
        }
        seedRanges.addAll(tempSeedRanges);
        seedRanges.sort(Comparator.comparingLong(SeedRange::getStart)); // Sorts based on the start
        System.out.println(seedRanges.get(0).getStart());
    }
}

class SeedRange {
    private long start;
    private long end;

    public SeedRange(long start, long end, String errorMsg) {
        if (start > end)
            System.out.println("Wrong construction, start: " + start + " end: " + end + " msg: " + errorMsg);

        this.start = start;
        this.end = end; // Calculate the end of the range
    }

    public char getOverlapp(long S, long SR) {
        if (S <= start && SR >= end) {
            return 'E'; // Entire overlapp
        } else if (S <= start && SR >= start && SR <= end) {
            return 'L'; // Left overlapp
        } else if (S >= start && S <= end && SR >= end) {
            return 'R'; // Right overlapp
        } else if (S >= start && S <= end && SR <= end) {
            return 'M'; // Middle
        } else { // No overlapp
            return '0';
        }
    }

    public long getStart() {
        return this.start;
    }

    public long getEnd() {
        return this.end;
    }

    public long getStartOverlapp(long S) {
        if (S <= start)
            return start;
        else if (S <= end) // Should never be false
            return S;
        System.out.println("error, getStartOverlapp!");
        return 0;
    }

    public long getEndOverlapp(long SR) {
        if (SR >= end)
            return end;
        else if (SR >= start) // Should never be false
            return SR;
        System.out.println("error getEndOverlap!");
        return 0;
    }
}