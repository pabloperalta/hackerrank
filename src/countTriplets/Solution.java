package countTriplets;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long, Long> vecesQueViK = new HashMap<>();
        HashMap<Long, Long> cantidadDeTripletsQueSeCompletanconK = new HashMap<>();
        long count = 0;

        for (long k : arr) {
            // Si k completa triplets (es 3er elemento de algun triplet), los sumo
            count += cantidadDeTripletsQueSeCompletanconK.getOrDefault(k, 0L);

            // Si k es elemento intermedio (tiene uno anterior) entonces dige que cuando aparezca el 3ro
            // la cantidad de triplets completados aumenta en tantas vece como haya visto al anterior
            if ((k % r) == 0 && vecesQueViK.get(k / r) != null) {
                cantidadDeTripletsQueSeCompletanconK.put(k * r, cantidadDeTripletsQueSeCompletanconK.getOrDefault(k * r, 0L) + vecesQueViK.get(k / r));
            }

            // Marco como visto a k para que los siguientes sepan que esta disponible para armar ternas
            vecesQueViK.merge(k, 1L, (a, b) -> a + b);
        }

        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\src\\countTriplets\\input06.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\countTriplets\\output.txt"));

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
