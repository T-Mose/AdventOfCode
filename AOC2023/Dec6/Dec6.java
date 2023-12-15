public class Dec6 {public class Dec6 {
    public static void main(String[] args) {
        // very little input so ill hard code it
        /* 
        int answer = 1;
        int t1 = 63;
        int t2 = 78;
        int t3 = 94;
        int t4 = 68;
        int d1 = 411;
        int d2 = 1274;
        int d3 = 2047;
        int d4 = 1035;

        answer *= solve(t1, d1);
        answer *= solve(t2, d2);
        answer *= solve(t3, d3);
        answer *= solve(t4, d4);

        System.out.println(answer);
        */ 
        // Part 2, the method also now is for long instead
        long distance = 411127420471035L;
        long time = 63789468;
        System.out.println(solve(time, distance));
    }
    public static long solve(long time, long distance) {
        long num = 0;
        for (long hold = 0; hold <= time; hold++) {
            long currDis = hold * (time - hold);
            if (currDis > distance)
                num++;
        }
        System.out.println(num);
        return num;
    }
}

