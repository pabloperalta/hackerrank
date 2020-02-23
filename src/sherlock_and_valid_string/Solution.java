package sherlock_and_valid_string;

import java.io.*;
import java.util.*;

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

        return (Objects.equals(max, min) || (max - min <= 1 && countMax == 1) || (countMax.equals(charFrequency.size() - 1) && min.equals(1))) ? "YES" : "NO";
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
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\sherlock_and_valid_string\\input13.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\sherlock_and_valid_string\\output.txt"));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
