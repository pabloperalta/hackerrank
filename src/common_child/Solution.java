package common_child;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    private static int max = 0;
    private static int counter = 0;

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        //https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
        //https://en.wikipedia.org/wiki/Dynamic_programming
        // Since there was a recursive aproach then dynamic programming could be use. Before solving a problem we check if we solved it before to sae time.
        // If I arrive to a word i already formed through a different path then I must have already calculated the max length I can get

        int[][] lcsMatrix = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            lcsMatrix[i][0] = 0;
            lcsMatrix[0][i] = 0;
        }
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    lcsMatrix[i + 1][j + 1] = lcsMatrix[i][j] + 1;
                } else {
                    lcsMatrix[i + 1][j + 1] = lcsMatrix[i + 1][j] > lcsMatrix[i][j + 1] ? lcsMatrix[i + 1][j] : lcsMatrix[i][j + 1];
                }
            }
        }
        return lcsMatrix[s1.length()][s1.length()];
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\common_child\\input14.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\common_child\\output.txt"));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

