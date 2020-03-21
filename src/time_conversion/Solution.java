package time_conversion;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        if (s.endsWith("AM")) {
            Integer pmHour = new Integer(s.substring(0, 2));
            return String.format("%02d" + s.substring(2, s.length() - 2), pmHour % 12);
        } else {
            Integer pmHour = new Integer(s.substring(0, 2));
            return String.format("%02d" + s.substring(2, s.length() - 2), (pmHour%12) + 12);
        }

    }

    private static final Scanner scan = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\time_conversion\\input00.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(".\\src\\time_conversion\\output.txt"));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
