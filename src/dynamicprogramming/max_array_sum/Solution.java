package dynamicprogramming.max_array_sum;

import java.io.*;
import java.util.Scanner;

public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int sumNoStepsBehind = arr[0];
        int sumOneStepBehind = 0;

        int currentMax;

        // Since there is a restriction of adjacency, we will contemplate two max values:
        // * The one we had one step behind that cannot interact with the current element because of the restriction
        // * The one we could have if we used the previous max that could interact with this element

        // Note that a max one step behind does not mean it used the previous element, it means it was the max until
        // that spot and since it is far away enough it can interact with us for sure.

        for (int i = 1; i < arr.length; i++) {
            System.out.println("No steps behind[" + sumNoStepsBehind + "] One steps behind[" + sumOneStepBehind + "]");

            // Here we see what is the real max so far
            if (sumNoStepsBehind > sumOneStepBehind) {
                currentMax = sumNoStepsBehind;
            } else {
                currentMax = sumOneStepBehind;
            }

            // The non restricte max in addition to our current element will be a candidate for max next iteration or by the
            // end of the execution, and will be restricted to be added to the next element
            sumNoStepsBehind = sumOneStepBehind + (arr[i]);

            sumOneStepBehind = currentMax;
            System.out.println("No steps behind[" + sumNoStepsBehind + "] One steps behind[" + sumOneStepBehind + "]");
            System.out.println();

        }

        // Comparing for the last time
        return Math.max(sumNoStepsBehind, sumOneStepBehind);
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\dynamicprogramming\\max_array_sum\\input\\input300.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\dynamicprogramming\\max_array_sum\\output.txt"));

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
