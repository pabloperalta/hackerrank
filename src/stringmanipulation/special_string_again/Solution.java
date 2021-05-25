package stringmanipulation.special_string_again;

import java.io.*;
import java.util.Scanner;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        int valids = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // First I check if all the characters are the same in strings with an even length
            // Check that all chars in front of the current one are the same
            // O(n/2)
            for (int j = 1; i + j < s.length(); j += 2) {

                if (s.charAt(i + j) != currentChar) {
                    // Once I found a different char, I can stop looking for string with all the same chars from s[i] as a starting point
                    break;
                }

                // While the chars are the same as the initial one, then the string has all the same chars
                logFoundValidString(s, i + j, i);
                valids++;
            }

            // Secondly I check the case of odd length string, than can have a mid char that differs from the rest
            Character sides = null;

            //O(n/2)
            for (int j = 0; i - j >= 0 && i + j < s.length(); j++) {

                // All string with a single char are valid
                if (j == 0) {
                    logFoundValidString(s, i + j, i - j);
                    valids++;
                    continue;
                }

                // If opposing chars are not the same, then there is no point in keeping evaluating from this position
                if ((s.charAt(i + j) != s.charAt(i - j))) {
                    break;
                }

                // I init the reference to the chars on my sides [i-1]<-[i]->[i+1]
                if (j == 1) {
                    sides = s.charAt(i + 1);
                }

                // If I got here, the opposing chars are the same. But if they are not the same as the reference char then this string is not valid nor will be the rest from this position
                if (sides != s.charAt(i + j)) {
                    break;
                }

                // Every symmetrically opposing char in this string is the same char.
                logFoundValidString(s, i + j, i - j);
                valids++;
            }

        }

        return valids;

    }

    private static void logFoundValidString(String s, int end, int start) {
        System.out.println("Found one from [" + start + "] to [" + end + "]: [" + s.substring(start, (end + 1)) + "]");
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\stringmanipulation\\special_string_again\\input16.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\stringmanipulation\\special_string_again\\output.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

