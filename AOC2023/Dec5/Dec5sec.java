import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
            line = null;
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR!: " + e.getMessage());
            System.exit(1);
        }

        long answer = Long.MAX_VALUE;
        String seeds1 = "763445965 78570222 1693788857 146680070 1157620425 535920936 3187993807 180072493 1047354752 20193861 2130924847 274042257 20816377 596708258 950268560 11451287 3503767450 182465951 3760349291 265669041";
        // String seeds1 = "79 14 55 13"; // Testing

        // Parse the input string into pairs
        String[] seedPairs = seeds1.split(" ");
        List<SeedRange> seedRanges = new ArrayList<>();
        List<SeedRange> tempSeedRanges = new ArrayList<>();

        for (int i = 0; i < seedPairs.length; i += 2) {
            long begin = Long.parseLong(seedPairs[i]);
            long range = Long.parseLong(seedPairs[i + 1]);
            seedRanges.add(new SeedRange(begin, begin + range - 1, "Start"));
        }
        int length = 0;
        for (SeedRange seedRange : seedRanges) {
            length += seedRange.getLength();
        }
        System.out.println(length + " start length!");

        for (String line : input) {
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(" ");
                long destination = Long.parseLong(parts[0]);
                long source = Long.parseLong(parts[1]);
                long range = Long.parseLong(parts[2]);
                for (int i = 0; i < seedRanges.size(); i++) {
                    SeedRange currentRange = seedRanges.get(i);
                    char overlapp = currentRange.getOverlapp(source, source + range - 1);
                    if (overlapp != '0') {
                        long overlappBegin = currentRange.getStartOverlapp(source);
                        long overlappEnd = currentRange.getEndOverlapp(source + range - 1);
                        long delta = source - destination;
                        switch (overlapp) {
                            case 'E': // Complete overlapp
                                tempSeedRanges.add(new SeedRange(currentRange.getStart() - delta,
                                        currentRange.getEnd() - delta, "complete"));
                                seedRanges.remove(currentRange);
                                i--; // Account for removal
                                System.out.println("te");
                                break;
                            case 'M': // Middle overlapp
                                SeedRange left = new SeedRange(currentRange.getStart(), overlappBegin,
                                        "left contained");
                                SeedRange middleNew = new SeedRange(destination, destination + range - 1,
                                        "left contained");
                                SeedRange right = new SeedRange(overlappEnd, currentRange.getEnd(), "right contained");

                                tempSeedRanges.add(middleNew);
                                seedRanges.set(i, left);
                                seedRanges.add(i + 1, right);
                                i++; // Since added
                                System.out.println("te");
                                break;
                            case 'L':
                                long lS = destination + range - 1 - (overlappEnd - currentRange.getStart());
                                SeedRange newLeft = new SeedRange(currentRange.getStart() - delta,
                                        destination + range - 1, "one-side left");
                                tempSeedRanges.add(newLeft);
                                seedRanges.set(i,
                                        new SeedRange(overlappEnd + 1, currentRange.getEnd(), "New one-sided left"));
                                System.out.println("te");
                                break;
                            case 'R':
                                long rEnd = destination + (source + range - 1) - currentRange.getEnd();
                                SeedRange newRight = new SeedRange(destination, currentRange.getEnd() - delta,
                                        "one-side right");
                                tempSeedRanges.add(newRight);
                                seedRanges.set(i, new SeedRange(currentRange.getStart(), overlappBegin - 1,
                                        "New one-sided right")); // 1
                                System.out.println("te");
                                break;
                            default:
                                System.out.println(overlapp + " somehing went wrong!");
                                break;
                        }
                    }
                }
            }
            if (!line.isEmpty() && Character.isLetter(line.charAt(0))) {
                // Merge tempSeedRanges into seedRanges
                System.out.println("Current size: " + seedRanges.size() + " new size: " + (seedRanges.size()
                        + tempSeedRanges.size()));
                seedRanges.addAll(tempSeedRanges);
                for (SeedRange seedRange : seedRanges) {
                    System.out.println(seedRange.getName());
                }
                tempSeedRanges.clear();
                System.out.println("te");
            }
        }
        seedRanges.addAll(tempSeedRanges);
        System.out.println(seedRanges.size() + " size of final list!");
        seedRanges.sort(Comparator.comparingLong(SeedRange::getStart)); // Sorts based on the start
        answer = seedRanges.get(0).getStart();
        length = 0; // reset from the begining
        for (SeedRange seedRange : seedRanges) {
            length += seedRange.getLength();
            System.out.println(seedRange.getName());
        }
        System.out.println(length);
        System.out.println(answer);
    }
}

class SeedRange {
    private long start;
    private long end;

    public SeedRange(long start, long end, String errorMsg) {
        if (start > end)
            System.out.println("Wrong construction, start:; " + start + " end: " + end + " msg: " + errorMsg);

        this.start = start;
        this.end = end; // Calculate the end of the range
    }

    public String getName() {
        return "Range: " + start + "_" + end;
    }

    public void setRange(long start, long end) {
        if (start > end)
            System.out.println("KNAS SET RANGE");
        start = this.start;
        end = this.end;
    }

    public char getOverlapp(long S, long SR) {
        // If no overlapp return 0, otherwise number decideds type of overlapp
        if (S <= start && SR >= end) {
            return 'E'; // Entire overlapp
        } else if (S <= start && SR >= start && SR <= end) {
            return 'L'; // Left overlapp
        } else if (S >= start && S <= end && SR >= end) {
            return 'R'; // Right overlapp
        } else if (S >= start && S <= end && SR <= end) {
            return 'M'; // Middle
        } else {
            return '0';
        }
    }

    public long getLength() {
        return (end - start) + 1;
    }

    public String displayRange() {
        return "Start: " + start + "_end: " + end;
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
        else if (S <= end)
            return S;
        System.out.println("KNAS getStartOverlapp");
        return 0;
    }

    public long getEndOverlapp(long SR) {
        if (SR >= end)
            return end;
        else if (SR >= start)
            return SR;
        System.out.println("KNAS getEndOverlap");
        return 0;
    }

    public long getSmallest() {
        return start;
    }
}