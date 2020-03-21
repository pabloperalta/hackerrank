package grading_students;

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
     * Complete the 'gradingStudents' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY grades as parameter.
     */

    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> result = new ArrayList<>();


        //Every student receives a grade in the inclusive range from 0 to 100.
        for (Integer grade : grades) {
            if (grade < 38) {
                //Any grade less than 40 is a failing grade.
                //If the value of grade is less than 38, no rounding occurs as the result will still be a failing grade
                result.add(grade);
            } else {

                //If the difference between the grade and the next multiple of 5 is less than 3, round grade up to the next multiple of 5.
                int remainder = grade % 5;
                int roundUp = 5 - remainder;
                if (roundUp < 3) {
                    result.add(grade + roundUp);
                } else {
                    result.add(grade);
                }
            }
        }


        //return an integer array consisting of rounded grades.
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\src\\grading_students\\input00.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\grading_students\\output.txt"));

        int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.gradingStudents(grades);

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
