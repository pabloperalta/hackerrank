package dynamic_array;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

/*
* Complete the 'dynamicArray' function below.
*
* The function is expected to return an INTEGER_ARRAY.
* The function accepts following parameters:
* 1. INTEGER n
* 2. 2D_INTEGER_ARRAY queries
*/

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
// Write your code here
        Stack<Integer> lastAnswers = new Stack<Integer>();
        List<Stack<Integer>> matrix = initializeMatrix(n);

        Integer peek;
        for (List<Integer> query : queries) {
            peek = getLastAnswer(lastAnswers);

            Integer queryType = query.get(0);
            Integer x = query.get(1);
            Integer y = query.get(2);

            if (queryType == 1) {
                appendAsIs(peek, n, matrix, x, y);
            } else {
                lastAnswers.push(appendSizeRemainder(peek, n, matrix, x, y));
            }
        }

        return lastAnswers;
    }

    private static Integer getLastAnswer(Stack<Integer> lastAnswers) {
        Integer peek;
        try {
            peek = lastAnswers.peek();
        } catch (EmptyStackException e) {
            peek = 0;
        }
        return peek;
    }

    private static void appendAsIs(Integer lastAnswer, int n, List<Stack<Integer>> matrix, Integer x, Integer y) {
        List<Integer> list = matrix.get((x ^ lastAnswer) % n);
        list.add(y);
    }

    private static Integer appendSizeRemainder(Integer lastAnswer, int n, List<Stack<Integer>> matrix, Integer x, Integer y) {
        Stack<Integer> list = matrix.get((x ^ lastAnswer) % n);
        return list.get(y % list.size());
    }

    private static List<Stack<Integer>> initializeMatrix(int n) {
        List<Stack<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            lists.add(new Stack<>());
        }

        return lists;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.dynamicArray(n, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}