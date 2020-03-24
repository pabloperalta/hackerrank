package minimum_time_required;

import java.io.*;
import java.util.Scanner;

public class Solution {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        long min = Long.MAX_VALUE;
        Double productionPerDay = 0d;

        for (long machine : machines) {
            productionPerDay += 1d / machine;

            if (machine < min) {
                min = machine;
            }
        }

        return binarySearch(machines, machines.length, goal, Double.valueOf(goal / productionPerDay).longValue(), goal * (machines.length / min));
    }

    // Binary search to find minimum time required to produce M items.
    static long binarySearch(long[] arr, int arrLength, long goal, long minimumDays, long maxDays) {
        System.out.println("Min [" + minimumDays + "] Max [" + maxDays + "]");
        // Doing binary search to find minimum time.
        while (minimumDays < maxDays) {
            // Finding the middle value.
            long mid = (minimumDays + maxDays) / 2L;
            System.out.println("Min [" + minimumDays + "] Max [" + maxDays + "] Mid[" + mid + "]");

            // Calculate number of items to be produce in mid days.
            long item = findItems(arr, arrLength, mid);
            System.out.println("In [" + mid + "] days [" + item + "] items would be produced and the goal is [" + goal + "]");

            if (item < goal) {
                // If items produce is less than required, set minimumDays = mid + 1.
                minimumDays = mid + 1;
            } else {
                // Else set maxDays = mid.
                maxDays = mid;
            }
        }

        return maxDays;
    }

    // Return the number of items can be produced in temp sec.
    static long findItems(long[] arr, long n, long temp) {
        long ans = 0;

        for (int i = 0; i < n; i++) {
            ans += (temp / arr[i]);
        }

        return ans;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\minimum_time_required\\input10.txt");
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
