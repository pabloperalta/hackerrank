package sorting.fraudulent_activity_notifications;

import java.io.*;
import java.util.Scanner;

public class Solution {

    public static final int MAX_EXPENDITURE = 200;

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        Integer[] trail = new Integer[d];
        for (int i = 0; i < d; ++i) {
            trail[i] = 0;
        }

        // Create a count array to store count of individual characters and initialize count array as 0
        int countsArray[] = new int[MAX_EXPENDITURE];
        int additive[] = new int[MAX_EXPENDITURE];
        for (int i1 = 0; i1 < MAX_EXPENDITURE; ++i1) {
            countsArray[i1] = 0;
            additive[i1] = 0;
        }

        for (int i = 0; i < expenditure.length; i++) {
            int currentExpenditure = expenditure[i];

            if (i >= d) {
                // The output intacter array that will have sorted arr
                int output[] = new int[d];

                additive[0] = countsArray[0];

                // Change count[i] so that count[i] now contains actual position of this character in output array
                for (int j = 1; j <= MAX_EXPENDITURE - 1; ++j) {
                    additive[j] = countsArray[j] + additive[j - 1];
                }

                // Build the output character array To make it stable we are operating in reverse order.
                for (int j = d - 1; j >= 0; j--) {
                    Integer trailElement = trail[j];
                    output[additive[trailElement] - 1] = trailElement;
                    --additive[trailElement];

                    if ((d % 2 == 1 && output[(d / 2) + 1] > 0) || (d % 2 == 0 && output[d / 2] > 0 && output[(d / 2) + 1] > 0)) {
                        break;
                    }
                }

                if (calculateMedian(d, output) * 2 <= currentExpenditure) {
                    count++;
                }

            }

            Integer rotatingElement = trail[i % d];

            if (rotatingElement >= 0) {
                countsArray[rotatingElement] = countsArray[rotatingElement] - 1;
            }

            trail[i % d] = currentExpenditure;
            ++countsArray[currentExpenditure];
        }

        return count;
    }

    private static double calculateMedian(int d, int[] output) {
        double median;
        if (d % 2 == 0) {
            median = getAverageMedian(d, output);
        } else {
            median = output[d / 2];
        }
        return median;
    }

    private static double getAverageMedian(int d, int[] sortedTrail) {
        return (double) (sortedTrail[d / 2] + sortedTrail[(d / 2) + 1]) / 2d;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\sorting\\fraudulent_activity_notifications\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\sorting\\fraudulent_activity_notifications\\output.txt"));

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

