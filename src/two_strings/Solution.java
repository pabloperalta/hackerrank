package two_strings;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String aux = s1;
            s1 = s2;
            s2 = aux;
        }

        HashSet<Character> charSet = new HashSet<>();

        for (int i = 0; i < s1.length(); i++) {
            charSet.add(s1.charAt(i));
        }

        for(Character character : charSet){
            if (s2.contains(String.valueOf(character))) {
                return "YES";
            }
        }
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println(twoStrings("Hola", "Ciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiu"));
    }
}

