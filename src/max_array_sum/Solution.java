package max_array_sum;

import java.io.*;
import java.util.Scanner;

public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int[][] matrix = new int[arr.length][arr.length];
        int result = Integer.MIN_VALUE;

        printHeader(arr);

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr.length; j++) {

                if(j==0){
                    System.out.print(arr[i] + "|");
                }

                if (j < i) {
                    System.out.print("X ");
                } else {
                    int currentValue = arr[j];

                    int oneSkip = calculateOneSkipBack(matrix, i, j);

                    int twoSkip = calculateTwoSkipBack(matrix, i, j);

                    int maxCombinationBackwards = Math.max(oneSkip, twoSkip);

                    int maxForThisElement = (currentValue > 0 ? currentValue : 0) + maxCombinationBackwards;
                    matrix[i][j] = maxForThisElement;

                    System.out.print(maxForThisElement + " ");

                    if (result < maxForThisElement) {
                        result = maxForThisElement;
                    }
                }
            }

            System.out.println();
        }

        return result;
    }

    private static void printHeader(int[] arr) {
        System.out.print("+ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static int calculateTwoSkipBack(int[][] matrix, int i, int j) {
        int twoSkip = 0;
        if (j - 3 >= 0) {
            twoSkip = matrix[i][j - 3];
        }
        return twoSkip;
    }

    private static int calculateOneSkipBack(int[][] matrix, int i, int j) {
        int oneSkip = 0;
        if (j - 2 >= 0) {
            oneSkip = matrix[i][j - 2];
        }
        return oneSkip;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\max_array_sum\\input\\input32.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\max_array_sum\\output.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
