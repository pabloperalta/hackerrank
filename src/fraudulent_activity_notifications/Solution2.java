package fraudulent_activity_notifications;

import java.io.*;
import java.util.*;

public class Solution2 {

    public static final int MAX_EXPENDITURE = 200;

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int count = 0;

        Integer[] trail = initTrail(d);

        int[] countsArray = initCountsArray();

        for (int i = 0; i < expenditure.length; i++) {
            int currentExpenditure = expenditure[i];

            if (i >= d) {
                double median = calculateMedian(d, countsArray);

                //trueMedianDebug(d, median, trail);

                if (median * 2 <= currentExpenditure) {
                    count++;
                }
            }

            updateTrail(d, trail, countsArray, i, currentExpenditure);
        }

        return count;
    }

    private static Integer[] initTrail(int d) {
        Integer[] trail = new Integer[d];
        for (int i = 0; i < d; ++i) {
            trail[i] = -1;
        }
        return trail;
    }

    private static int[] initCountsArray() {
        int countsArray[] = new int[MAX_EXPENDITURE];

        for (int i1 = 0; i1 < MAX_EXPENDITURE; ++i1) {
            countsArray[i1] = 0;
        }
        return countsArray;
    }

    private static void updateTrail(int d, Integer[] trail, int[] countsArray, int i, int currentExpenditure) {
        Integer rotatingElement = trail[i % d];

        if (rotatingElement >= 0) {
            countsArray[rotatingElement] = countsArray[rotatingElement] - 1;
        }

        trail[i % d] = currentExpenditure;
        ++countsArray[currentExpenditure];
    }

    private static double calculateMedian(int d, int[] countsArray) {
        if (isOdd(d)) {
            return findOddMedian(countsArray, (d / 2) + 1);
        }

        return findEvenMedian(d, countsArray);
    }

    private static double findEvenMedian(int d, int[] countsArray) {
        double median;
        int offset = 0;
        int term1 = 0;
        int term2 = 0;
        for (int j = 0; j < MAX_EXPENDITURE; j++) {
            int currentElementCount = countsArray[j];

            int lowTermIndex = d / 2;

            if (term1 == 0 && offset < lowTermIndex && currentElementCount + offset > lowTermIndex) {
                term1 = j;
                term2 = j;
                break;
            } else if (term1 == 0 && currentElementCount + offset == lowTermIndex) {
                term1 = j;
            } else if (offset == lowTermIndex && currentElementCount != 0) {
                term2 = j;
                break;
            }

            offset = currentElementCount + offset;
        }

        median = (double) (term1 + term2) / 2d;
        return median;
    }

    private static double findOddMedian(int[] countsArray, int position) {
        int offset = 0;

        for (int j = 0; j < MAX_EXPENDITURE; j++) {

            int currentElementRelativeOffset = countsArray[j];


            if (offset < position && position <= currentElementRelativeOffset + offset) {
                return j;
            }

            offset = currentElementRelativeOffset + offset;
        }

        return offset;
    }

    private static boolean isOdd(int d) {
        return d % 2 == 1;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\fraudulent_activity_notifications\\input05.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\fraudulent_activity_notifications\\output2.txt"));

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

