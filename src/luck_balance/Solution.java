package luck_balance;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        Arrays.sort(contests, Comparator.comparingInt(o -> o[0]));

        int savedLuck = 0;

        for (int i = contests.length - 1; i >= 0; i--) {
            int[] contest = contests[i];
            int luckValue = contest[0];
            int importance = contest[1];

            if (importance == 0) {
                savedLuck += luckValue;
            } else if (k > 0) {
                k--;
                savedLuck += luckValue;
            } else {
                savedLuck -= luckValue;
            }

        }

        return savedLuck;

    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\luck_balance\\input12.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\luck_balance\\output.txt"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

