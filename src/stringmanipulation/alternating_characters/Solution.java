package stringmanipulation.alternating_characters;

import java.io.*;
import java.util.Scanner;

public class Solution {

    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
        int toDelete = 0;

        Character current = null;

        for (int i = 0; i < s.length(); i++) {
            if (current != null && s.charAt(i) == current) {
                toDelete++;
            } else {
                current = s.charAt(i);
            }
        }

        return toDelete;

    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\stringmanipulation\\alternating_characters\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\stringmanipulation\\alternating_characters\\output.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = alternatingCharacters(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

