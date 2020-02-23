package counting_inversions;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class Solution {
    private static long countSwaps = 0;

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        countSwaps = 0;
        sort(arr, 0, arr.length - 1);
//        System.out.println("Total inversions: " + countSwaps);
//        System.out.println();
        return countSwaps;
    }

    // Merges two subarrays of inputArray[].
    // First subarray is inputArray[leftIndex..middleIndex]
    // Second subarray is inputArray[middleIndex+1..rightIndex]
    static void merge(int inputArray[], int leftIndex, int middleIndex, int rightIndex) {
        // Find sizes of two subarrays to be merged
        int leftArraySize = middleIndex - leftIndex + 1;
        int rightArraySize = rightIndex - middleIndex;


        /* Create temp arrays */
        /*Copy data to temp arrays*/
        int[] leftArray = copyLeftArray(inputArray, leftIndex, leftArraySize);
        int[] rightArray = copyRightArray(inputArray, middleIndex, rightArraySize);

        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
//        System.out.println("Left Array: " + Arrays.toString(leftArray));
//        System.out.println("Right Array: " + Arrays.toString(rightArray));
        int k = leftIndex;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i] <= rightArray[j]) {
                inputArray[k++] = leftArray[i++];
            } else {
//                System.out.println("Found Inversion: " + leftArray[i] + " - " + rightArray[j]);
//                System.out.println("Current: " + countSwaps + " Adding " + (leftArraySize - i) + " New count: " + (countSwaps + middleIndex + 1 - i));
                countSwaps += leftArraySize - i;
                inputArray[k++] = rightArray[j++];
            }
        }

//        System.out.println();

        /* Copy remaining elements of leftArray[] if any */
        while (i < leftArraySize) {
            inputArray[k] = leftArray[i];
            i++;
            k++;
        }

        /* Copy remaining elements of rightArray[] if any */
        while (j < rightArraySize) {
            inputArray[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static int[] copyRightArray(int[] inputArray, int middleIndex, int rightArraySize) {
        int rightArray[] = new int[rightArraySize];
        for (int j = 0; j < rightArraySize; ++j) {
            rightArray[j] = inputArray[middleIndex + 1 + j];
        }
        return rightArray;
    }

    private static int[] copyLeftArray(int[] inputArray, int leftIndex, int leftArraySize) {
        int leftArray[] = new int[leftArraySize];
        for (int i = 0; i < leftArraySize; ++i) {
            leftArray[i] = inputArray[leftIndex + i];
        }
        return leftArray;
    }

    // Main function that sorts inputArray[leftIndex..rightIndex] using
    // merge()
    static void sort(int inputArray[], int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            // Find the middle point
            int middleIndex = (leftIndex + rightIndex) / 2;

            // Sort first and second halves
            sort(inputArray, leftIndex, middleIndex);
            sort(inputArray, middleIndex + 1, rightIndex);

            // Merge the sorted halves
            merge(inputArray, leftIndex, middleIndex, rightIndex);
        }
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\counting_inversions\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\counting_inversions\\output.txt"));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

