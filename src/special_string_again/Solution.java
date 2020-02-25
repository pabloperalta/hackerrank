package special_string_again;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long valids = 0;

        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> charCount = new HashMap<>();
            char firstChar = s.charAt(i);

            for (int j = i; j < s.length(); j++) {
                //Count J character
                charCount.merge(s.charAt(j), 1, (x, y) -> x + y);

                // Cheap enough but the whole thing could be optimized more implementing a proper sliding window
                if (charCount.keySet().size() > 2 || (charCount.values().size() == 2 && charCount.values().stream().noneMatch(x -> x == 1))) {
                    break;
                }

                int currentStringLength = (j + 1) - i;

                if (currentStringLength == charCount.get(firstChar)) {
//                        System.out.println("Found " + s.substring(i, j + 1));
                    valids++;
                } else if (currentStringLength > 0 && currentStringLength % 2 == 1) {
                    //Remove the mid character from the count
                    char midChar = s.charAt(i + (currentStringLength / 2));
                    charCount.merge(midChar, 1, (x, y) -> x - y);

                    if (charCount.get(firstChar) == (currentStringLength - 1)) {
//                            System.out.println("Found " + s.substring(i, j + 1));
                        valids++;
                    }

                    //Restore the mid character to the count
                    charCount.merge(midChar, 1, (x, y) -> x + y);
                }
            }

            // Remove i character from the count
            charCount.merge(firstChar, 1, (x, y) -> x - y);
        }

//        System.out.println("Total: " + valids);
        return valids;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\special_string_again\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\special_string_again\\output.txt"));

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

