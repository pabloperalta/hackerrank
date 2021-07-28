package problemsolving.the_birthday_bar;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'birthday' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY s
     *  2. INTEGER d
     *  3. INTEGER m
     */

    public static int birthday(List<Integer> s, int d, int m) {
        // Write your code here
        int count = 0;

        List<Long> prefix = new ArrayList<>();
        Long sum = 0L;
        for (int i = 0; i < s.size(); i++) {
            sum += s.get(i);
            prefix.add(sum);
        }

        for (int i = 0; i < s.size() - (m-1); i++) {
            long sumOfChocolate = prefix.get(i + m - 1) - (i > 0 ? prefix.get(i - 1) : 0);
            System.out.println("Testing ["+i+"]-["+(i+m-1)+"] = [" + sumOfChocolate + "]" );
            if (sumOfChocolate == d) {
                System.out.println("Is solution");
                count++;
            }
        }

        return count;
    }

}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\problemsolving\\the_birthday_bar\\input\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\problemsolving\\the_birthday_bar\\output.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
