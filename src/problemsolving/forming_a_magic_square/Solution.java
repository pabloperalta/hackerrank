package problemsolving.forming_a_magic_square;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static final int MAGIC = 15;

    /*
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY s as parameter.
     */


    private static class MagicSquare {
        private int ax;
        private int ay;
        private int az;
        private int bx;
        private int by;
        private int bz;
        private int cx;
        private int cy;
        private int cz;
    }
}

public class Solution {
//    private static InputStream getSource() {
//        try {
//            return new FileInputStream(".\\src\\problemsolving\\forming_a_magic_square\\input\\input01.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return System.in;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\problemsolving\\forming_a_magic_square\\output.txt"));
//
//        List<List<Integer>> s = new ArrayList<>();
//
//        IntStream.range(0, 3).forEach(i -> {
//            try {
//                s.add(
//                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                                .map(Integer::parseInt)
//                                .collect(toList())
//                );
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//
//        int result = Result.formingMagicSquare(s);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
}
