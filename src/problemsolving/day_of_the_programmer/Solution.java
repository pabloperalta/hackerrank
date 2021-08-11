package problemsolving.day_of_the_programmer;

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
     * Complete the 'dayOfProgrammer' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER year as parameter.
     */

    public static String dayOfProgrammer(int year) {
        // Write your code here
        Calendar instance = new Calendar.Builder().setCalendarType("gregory").set(Calendar.YEAR, year).build();
        int days = 256;

        if(year <= 1918 && year % 4 == 0 && year % 100 == 0 && year % 400 != 0){
            days--;
        }

        if(year == 1918){
            days += 13;
        }

        instance.set(Calendar.DAY_OF_YEAR, days);
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(time);
    }

}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\problemsolving\\day_of_the_programmer\\input\\input59.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\problemsolving\\day_of_the_programmer\\output.txt"));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.dayOfProgrammer(year);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
