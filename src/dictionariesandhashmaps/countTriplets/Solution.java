package dictionariesandhashmaps.countTriplets;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long, Long> vecesQueViK = new HashMap<>();
        HashMap<Long, Long> cantidadDeTripletsQueSeCompletanconK = new HashMap<>();
        long count = 0;

        for (long k : arr) {
            // If k completes a triplet (is the third element of any triplet) then I add it
            count += cantidadDeTripletsQueSeCompletanconK.getOrDefault(k, 0L);

            // If k is a middle element (has a previous one) then, when the third one shows up the amount of completes
            // triplets will increase as many times as possible previous elements for k I have already seen
            if ((k % r) == 0 && vecesQueViK.get(k / r) != null) {
                cantidadDeTripletsQueSeCompletanconK.put(k * r, cantidadDeTripletsQueSeCompletanconK.getOrDefault(k * r, 0L) + vecesQueViK.get(k / r));
            }

            // K is counted as seen so that the following elements know it is available to form triplets
            vecesQueViK.merge(k, 1L, (a, b) -> a + b);
        }

        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\src\\dictionariesandhashmaps\\countTriplets\\input06.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\dictionariesandhashmaps\\countTriplets\\output.txt"));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
