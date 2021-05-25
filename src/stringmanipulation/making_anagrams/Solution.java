package stringmanipulation.making_anagrams;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        Map<Character, Integer> charCount = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            charCount.merge(a.charAt(i), 1, (x, y) -> x + y);
        }

        for (int i = 0; i < b.length(); i++) {
            charCount.merge(b.charAt(i), -1, (x, y) -> x + y);
        }

        int total = 0;
        for (Integer count : charCount.values()) {
            total += Math.abs(count);
        }

        return total;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\stringmanipulation\\making_anagrams\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\stringmanipulation\\making_anagrams\\output.txt"));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
