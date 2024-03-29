package search.pairs;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    // Complete the search.pairs function below.
    static int pairs(int k, int[] arr) {
        int count = 0;
        Arrays.sort(arr);

        int i = 0, j = 1;
        while (j < arr.length) {
            int diff = arr[j] - arr[i];

            if (diff == k) {
                count++;
                i++;
            } else if (diff < k) {
                j++;
            } else {
                i++;
            }

            if (i == j) {
                j++;
            }
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\search\\pairs\\input115.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\search\\pairs\\output.txt"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

