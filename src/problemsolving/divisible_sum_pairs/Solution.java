package problemsolving.divisible_sum_pairs;

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
     * Complete the 'divisibleSumPairs' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER_ARRAY ar
     */

    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
        List<Pair> pairList = new ArrayList<>();

        for (int i = 0; i < ar.size(); i++) {
            pairList.add(new Pair(i, ar.get(i)));
        }

        int total = 0;

        for (int i = 0; i < ar.size() - 1; i++) {
            for (int j = i + 1; j < ar.size(); j++) {
                if (pairList.get(i).getIndex() < pairList.get(j).getIndex() && (pairList.get(i).getValue() + pairList.get(j).getValue()) % k == 0) {
                    total++;
                }
            }
        }

        return total;

    }

    private static class Pair {
        private int index;
        private int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }
}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\problemsolving\\divisible_sum_pairs\\input\\input12.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\problemsolving\\divisible_sum_pairs\\output.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> ar = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.divisibleSumPairs(n, k, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
