package stringmanipulation.sherlock_and_valid_string;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        Integer max = 0;
        Integer min = Integer.MAX_VALUE;
        Integer countMax = 0;

        if (s.isEmpty()) {
            return "YES";
        }

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            Integer newValue = getIncrementedCount(charFrequency, key);

            charFrequency.put(key, newValue);

            if (newValue > max) {
                max = newValue;
            }
        }

        for (Character key : charFrequency.keySet()) {
            Integer freq = charFrequency.get(key);
//            System.out.println("Key: " + key + " - Value: " + freq);
            if (freq < min) {
                min = freq;
            }

            if (freq.equals(max)) {
                countMax++;
            }
        }

//        System.out.println("Max: " + max + " Min: " + min + " MaxCount: " + countMax);

        return (
                //No need to delete one char
                Objects.equals(max, min)
                        // One char appears one more time too many
                        || (max - min <= 1 && countMax == 1)
                        // Corner case (eg: aaaaaaaaaaaz) I can outright eliminate a char and the remaining string will be all the same chars
                        || (countMax.equals(charFrequency.size() - 1) && min.equals(1))
        ) ? "YES" : "NO";
    }

    private static Integer getIncrementedCount(Map<Character, Integer> charFrequency, char key) {
        Integer current = charFrequency.get(key);

        if (current == null) {
            return 1;
        }

        return current + 1;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\stringmanipulation\\sherlock_and_valid_string\\input13.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\stringmanipulation\\sherlock_and_valid_string\\output.txt"));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
