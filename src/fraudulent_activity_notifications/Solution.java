package fraudulent_activity_notifications;

import java.io.*;
import java.util.*;

public class Solution {

    public static final int MAX_EXPENDITURE = 200;

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        List<Integer> trail = new ArrayList<>();


        for (int i = 0; i < expenditure.length; i++) {

            if (i >= d) {
                double result = 0;

                int[] sortedTrail = sort(trail.toArray(new Integer[d]), d);

                if (d % 2 == 0) {
                    result = getAverageMedian(d, sortedTrail);
                } else {
                    result = sortedTrail[d / 2];
                }

                double median = result;


                if (median * 2 <= expenditure[i]) {
                    count++;
                }

                Integer index = expenditure[i - d];
                trail.remove(index);
            }

            trail.add(expenditure[i]);

        }

        return count;
    }

    private static double getAverageMedian(int d, int[] sortedTrail) {
        return (double) (sortedTrail[d / 2] + sortedTrail[(d / 2) + 1]) / 2d;
    }

    static int[] sort(Integer arr[], int n) {

        // The output intacter array that will have sorted arr
        int output[] = new int[n];

        // Create a count array to store count of inidividal characters and initialize count array as 0
        int count[] = new int[MAX_EXPENDITURE];
        for (int i = 0; i < MAX_EXPENDITURE; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i) {
            ++count[arr[i]];
        }

        // Change count[i] so that count[i] now contains actual position of this character in output array
        for (int i = 1; i <= MAX_EXPENDITURE - 1; ++i) {
            count[i] += count[i - 1];
        }

        // Build the output character array To make it stable we are operating in reverse order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        return output;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\fraudulent_activity_notifications\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\fraudulent_activity_notifications\\output.txt"));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

