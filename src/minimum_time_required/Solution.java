package minimum_time_required;

import java.io.*;
import java.util.Scanner;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {

        long minimumDays = minimumDays(machines, goal);
        long days = minimumDays;

        int production = 0;
        while (production < goal) {
            production = 0;

            for (int i = 0; i < machines.length; i++) {
                production += days / machines[i];
            }

            days++;
        }

        System.out.println("Iterations [" + (days - minimumDays) + "]");

        return days - 1;

    }

    private static long minimumDays(long[] machines, long goal) {
        Double productionPerDay = 0d;

        for (int i = 0; i < machines.length; i++) {
            productionPerDay += 1d / machines[i];
        }

        System.out.println("Goal [" + goal + "] production per day [" + productionPerDay + "] minimum days [" + Double.valueOf(goal / productionPerDay).longValue() + "]");
        return Double.valueOf(goal / productionPerDay).longValue();
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\minimum_time_required\\input100.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\minimum_time_required\\output.txt"));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
