package warmup.counting_valleys;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        int valleys = 0;
        int height = 0;

        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'U':
                    height++;

                    if (height == 0) {
                        valleys++;
                    }
                    break;
                default:
                    height--;
            }
        }

        return valleys;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\warmup\\counting_valleys\\output.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

