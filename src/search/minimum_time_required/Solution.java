package search.minimum_time_required;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        long max = Long.MIN_VALUE;
        Double productionPerDay = 0d;

        for (long machine : machines) {
            productionPerDay += 1d / machine;

            if (max < machine) {
                max = machine;
            }
        }

        // This is a meager optimization over using min=1 and max=MAX_LONG
        long minimumDays = calculateMinimunDays(goal, productionPerDay);
        long maxDays = calculateMaximunDays(machines, goal, max);

        return binarySearch(machines, goal, minimumDays, maxDays);
    }

    private static long calculateMaximunDays(long[] machines, long goal, double max) {
        double worstCaseAverage = max / machines.length;
        return ((Double) ceil(goal * worstCaseAverage)).longValue();
    }

    private static long calculateMinimunDays(long goal, Double productionPerDay) {
        double average = goal / productionPerDay;
        return ((Double) floor(average)).longValue();
    }

    // Binary search to find minimum time required to produce M items.
    static long binarySearch(long[] machines, long goal, long minimumDays, long maxDays) {
        long count = 0;
        System.out.println("Min [" + minimumDays + "] Max [" + maxDays + "]");

        // Doing binary search to find minimum time.
        while (minimumDays < maxDays) {
            count++;

            // Finding the middle value.
            long mid = (minimumDays / 2L) + (maxDays / 2L);
            //System.out.println("Min [" + minimumDays + "] Max [" + maxDays + "] Mid[" + mid + "]");

            // Calculate number of items to be produce in mid days.
            long item = productionToADay(machines, mid);

            if (item < goal) {
                System.out.println("LOW In [" + mid + "] days [" + item + "] items would be produced and the goal is [" + goal + "]");
                // If items produce is less than required, set minimumDays = mid + 1.
                minimumDays = mid + 1;
            } else {
                System.out.println("HIGH In [" + mid + "] days [" + item + "] items would be produced and the goal is [" + goal + "]");
                // Else set maxDays = mid.
                maxDays = mid;
            }
        }

        System.out.println("Answer [" + maxDays + "] Took [" + count + "] iterations");
        return maxDays;
    }

    // Return the number of items can be produced in temp sec.
    static long productionToADay(long[] machines, long day) {
        return Arrays.stream(machines).map(machine -> (day / machine)).sum();
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\search.minimum_time_required\\input10.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\search.minimum_time_required\\output.txt"));

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
