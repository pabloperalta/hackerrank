package dynamicprogramming.abbreviation;

import java.io.*;
import java.util.stream.IntStream;

class Result {

    /*
     * Complete the 'dynamicprogramming.abbreviation' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    /**
     * The approach is similar to common child (find the common subsequence)
     * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
     * <p>
     * Example: a[bBccC] and b[BBC]
     * <p>
     * <p>
     * + +++++ b      B      c      c      C
     * + true  true  false false false false
     * B false true  true  true  true  false
     * B false false true  true  true  false
     * C false false false true  true  true
     * <p>
     * This matrix has an extra first column and first line
     * <p>
     * <p>
     * if (b) were empty, everything would be ok with the char 'b' in (a) because I can delete it. But as soon as I run
     * into a capital letter in (a) such as 'B' the validity is lost. So everything in that line is false from there.
     * <p>
     * Now we imagine (b) has only one char: "B". Lets go to [1][1]. The char 'b' in (a) can match 'B'. Since the
     * diagonal is true, it means that if I use those two chars to match each other, the dynamicprogramming.abbreviation will be valid so
     * far.
     * <p>
     * We keep going and see that everything stays OK until I run into the 'C' char in (a). I cannot discard it nor
     * match it so [5][1] is false.
     * <p>
     * Taking (b) as BB produces similar result
     * <p>
     * But BBC becomes valid as soon as we run into the first lowercase 'c'. The second one is lowercase so we can
     * discard any of them and still be valid. So if BB was valid until bBc then BBC will be valid in bBcc.
     * <p>
     * Once we run into 'C' we realize that the string is valid because if the char matches and BB was valid until bBcc,
     * then we can use the final C.
     *
     * @param a
     * @param b
     * @return
     */
    static String abbreviation(String a, String b) {
        boolean[][] isAbbreviationMatrix = new boolean[b.length() + 1][a.length() + 1];

        isAbbreviationMatrix[0][0] = true;

        // Sets every element in the first column at 0
        for (int j = 1; j < isAbbreviationMatrix[0].length; j++) {
            if (Character.isLowerCase(a.charAt(j - 1))) {
                isAbbreviationMatrix[0][j] = isAbbreviationMatrix[0][j - 1];
            }
        }

        for (int i = 1; i < isAbbreviationMatrix.length; i++) {
            for (int j = 1; j < isAbbreviationMatrix[0].length; j++) {

                char ca = a.charAt(j - 1);
                char cb = b.charAt(i - 1);

                if (Character.isUpperCase(ca)) {
                    if (ca == cb) {
                        // If the character from the A string is an uppercase char that matches with B char, then so far so good
                        isAbbreviationMatrix[i][j] = isAbbreviationMatrix[i - 1][j - 1];
                    }
                } else {
                    ca = Character.toUpperCase(ca);
                    if (ca == cb) {
                        // If the character from the A string is a lowercase char that matches with B char, then I might still discard it
                        isAbbreviationMatrix[i][j] = isAbbreviationMatrix[i - 1][j - 1] || isAbbreviationMatrix[i][j - 1];
                    } else {
                        // Discarded
                        isAbbreviationMatrix[i][j] = isAbbreviationMatrix[i][j - 1];
                    }
                }
            }
        }

        //printMatrix(a, b, isAbbreviationMatrix);

        return isAbbreviationMatrix[b.length()][a.length()] ? "YES" : "NO";
    }

    private static void printMatrix(String a, String b, boolean[][] isAbbreviationMatrix) {
        printHeader(a);

        for (int i = 0; i < isAbbreviationMatrix.length; i++) {
            printLines(b, i);

            for (int j = 0; j < isAbbreviationMatrix[0].length; j++) {
                boolean bool = isAbbreviationMatrix[i][j];
                System.out.print(bool ? bool + "  " : bool + " ");
            }
            System.out.println();
        }
    }

    private static void printLines(String b, int i) {
        if (i == 0) {
            System.out.print("+ ");
        } else {
            System.out.print(b.charAt(i - 1) + " ");
        }
    }

    private static void printHeader(String a) {
        System.out.print("+ +++++ ");
        for (int i = 0; i < a.length(); i++) {
            System.out.print(a.charAt(i) + "      ");
        }
        System.out.println();
    }

}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\dynamicprogramming\\abbreviation\\input\\input11.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\dynamicprogramming\\abbreviation\\output.txt"));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = Result.abbreviation(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
