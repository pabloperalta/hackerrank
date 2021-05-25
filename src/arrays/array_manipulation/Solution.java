package arrays.array_manipulation;

import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long max = Integer.MIN_VALUE;
        long[] cascadeSumDescriptor = new long[n + 1], resultArray = new long[n];

        for (int[] q : queries) {
            int a = q[0];
            int b = q[1];
            int k = q[2];

            // Encoding an "increase" or "decrease" of value
            // When sweeping forward x will eventually be added to all following columns
            cascadeSumDescriptor[a - 1] += k;

            // Encoding the end of the "increase" or "decrease" of value
            // When sweeping forward x addition will be cancelled by this substraction
            cascadeSumDescriptor[b - 1 + 1] -= k;
        }

        // Kindof like a reduce. Starts from the initial cascadeSumDescriptor[0] value and acumulates by addition. Keeps track of the maximum.
        for (int i = 0; i < resultArray.length; i++) {

            if (i == 0) {
                // Start element
                resultArray[i] = cascadeSumDescriptor[i];
            } else {
                // Every element will be the sum of the previous ones. This optimizes the additions that are now just one per element + one for each query.
                resultArray[i] = cascadeSumDescriptor[i] + resultArray[i - 1];

                if (resultArray[i] > max) {
                    max = resultArray[i];
                }
            }
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = 10;
        int[][] queries = {
                {1, 1, 1},
                {2, 7, 10},
                {4, 5, 10},
                {10, 10, 10},
        };
        long result2 = arrayManipulation(n, queries);
        System.out.println("Result[" + result2 + "]");
    }
}
