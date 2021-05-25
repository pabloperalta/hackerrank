package dictionariesandhashmaps.ransom_note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        ArrayList<String> magazineWords = new ArrayList<>();
        magazineWords.addAll(Arrays.asList(magazine));
        List<String> noteWords = Arrays.asList(note);

        for (String word : noteWords) {
            if (!magazineWords.remove(word)) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] magazine = {"Hola", "No", "Funciona"};
        String[] note = {"Hola", "No", "Funciona"};

        checkMagazine(magazine, note);
    }
}

