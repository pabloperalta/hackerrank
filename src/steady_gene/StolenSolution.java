package steady_gene;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StolenSolution {

    static int steadyGene(String gene) {
        Map<Character, Integer> map = countOcurrences(gene);

        int left = 0, right = 0, min = Integer.MAX_VALUE;

        while (right < gene.length()) {
            // Tomo un caracter y agrando el string a retirar
            char innerCharacter = gene.charAt(right++);

            // Lo descuento del total porque lo estaria sacando
            map.put(innerCharacter, map.get(innerCharacter) - 1);

            // Los descontados son el string a retirar
            // Este se agranda a medida que lo que quede afuera no sea valido
            // Si lo corro tanto que llego al final, termino.
            // Si los restantes despues del descuento son un string equilibrado entonces
            while (isSteady(map, gene.length()) && left < gene.length()) {
                // Actualizo el minimo si es el string que desconte hasta ahora
                min = Math.min(min, right - left);

                //Tomo el caracter que quedo a la izquierda
                char reintitutedCharacter = gene.charAt(left++);

                // Lo sumo. Esto va corriendo el string como una oruga a la derecha
                map.put(reintitutedCharacter, map.get(reintitutedCharacter) + 1);
            }
        }
        return min;
    }

    private static Map<Character, Integer> countOcurrences(String gene) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 0);
        map.put('G', 0);
        map.put('T', 0);

        for (Character c : gene.toCharArray()) {
            map.put(c, map.get(c) + 1);
        }
        return map;
    }

    private static boolean isSteady(Map<Character, Integer> map, int length) {
        for (Integer i : map.values())
            if (i > length / 4) return false;
        return true;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\steady_gene\\input02.txt");
        } catch (FileNotFoundException e) {
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\steady_gene\\output.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
