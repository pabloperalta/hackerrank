package common_child;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    private static int max = 0;
    private static int counter = 0;

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        max = 0;
        counter = 0;
        findLongestPath(s1, 0, s2, 0);
        return max;
    }

    private static void findLongestPath(String s1, int s1Offset, String s2, int s2Offset) {
        for (int i = s1Offset; i < s1.length(); i++) {
            for (int j = s2Offset; j < s2.length(); j++) {
                char s2Char = s2.charAt(j);
                if (s2Char == s1.charAt(i)) {

                    counter++;

                    int nextCharRelativePosition = IntStream.range(s1Offset, s1.length()).filter(k -> s1.charAt(k) == s2Char).findFirst().orElse(-1);

                    if (nextCharRelativePosition >= 0) {

                        findLongestPath(s1, nextCharRelativePosition + 1, s2, j + 1);

                        if (counter > max) {
                            max = counter;
                        }
                    }

                    counter--;
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\common_child\\input14.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\common_child\\output.txt"));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

