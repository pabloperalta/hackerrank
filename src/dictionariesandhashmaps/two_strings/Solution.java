package dictionariesandhashmaps.two_strings;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

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

        for (Character character : charSet) {
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

