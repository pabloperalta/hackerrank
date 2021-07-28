package problemsolving.migratory_birds;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'migratoryBirds' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int migratoryBirds(List<Integer> arr) {
        // Write your code here
        Map<Integer, Long> countBirds = new HashMap<>();

        for (Integer birdId: arr ) {
            countBirds.merge(birdId, 1L, (a, b) -> a+b);
        }

        List<Integer> collect = countBirds.keySet().stream().distinct().sorted((a, b) -> {
            long diff = countBirds.get(b) - countBirds.get(a);
            if (diff > 0) {
                return 1;
            }
            if (diff == 0L) {
                return a - b;
            }
            if (diff < 0) {
                return -1;
            }

            return 0;
        }).collect(toList());

        return collect.get(0);
    }

}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\problemsolving\\migratory_birds\\input\\input05.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\problemsolving\\migratory_birds\\output.txt"));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.migratoryBirds(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

