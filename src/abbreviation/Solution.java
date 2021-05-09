package abbreviation;

import java.io.*;
import java.util.stream.IntStream;

class Result {

    /*
     * Complete the 'abbreviation' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static String abbreviation(String a, String b) {
        System.out.println("A[" + a + "] B[" + b + "]");

        if (a.length() < b.length()) {
            // I can only capitalize or remove letters. There is no way to make b from a if a has not enough letters
            System.out.println("A is too short");
            System.out.println();
            return "NO";
        }

        int ai = 0;
        int bi = 0;

        while (ai < a.length() || bi < b.length()) {
            if (ai >= a.length()) {
                System.out.println("Could not find a match for [" + b.charAt(bi) + "]");
                System.out.println();
                return "NO";
            }

            if (bi < b.length()) {
                char currentChar = b.charAt(bi);

                if (a.charAt(ai) == currentChar || Character.toUpperCase(a.charAt(ai)) == currentChar) {
                    System.out.println("Found [" + a.charAt(ai) + "] in a for [" + currentChar + "]");
                    bi++;
                } else {
                    if (Character.isUpperCase(a.charAt(ai))) {
                        System.out.println("I cannot delete [" + a.charAt(ai) + "] and it has no oportune match");
                        System.out.println();
                        return "NO";
                    }
                }

                ai++;
            } else {
                if (Character.isUpperCase(a.charAt(ai))) {
                    System.out.println("I cannot delete [" + a.charAt(ai) + "] and it has no oportune match");
                    System.out.println();
                    return "NO";
                }
                ai++;
            }
        }

        System.out.println("It is possible!");
        System.out.println();
        return "YES";

    }

}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\abbreviation\\input\\input01.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\abbreviation\\output.txt"));

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
