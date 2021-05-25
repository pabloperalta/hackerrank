package dictionariesandhashmaps.sherlock_and_anagrams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        List<Word> allWords = new ArrayList<>();
        int anagramPairs = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                Word word = asWord(s, i, j);

                for (Word x : allWords) {
                    if (x.isAnagram(word) && word.size() < s.length()) {
                        anagramPairs++;
                    }
                }

                allWords.add(word);
            }
        }

        return anagramPairs;
    }

    private static class Word extends ArrayList<Letter> {
        boolean isAnagram(Object o) {
            if (!(o instanceof Word)) {
                return false;
            }

            Word input = (Word) o;

            return input.size() == this.size() && !differentWord(input) && differentLetters(input);

        }

        // But the letters dont have the same original index (they are different letters)
        private boolean differentLetters(Word input) {
            return IntStream.range(0, this.size()).anyMatch(i -> this.get(i).getIndex() != input.get(i).getIndex());
        }

        // Once sorted is the same word
        private boolean differentWord(Word input) {
            return IntStream.range(0, this.size()).anyMatch(i -> !this.get(i).getCharacter().equals(input.get(i).getCharacter()));
        }

    }

    private static Word asWord(String s, int i, int j) {
        Word list = new Word();
        for (int k = i; k < j + 1; k++) {
            list.add(new Letter(k, s.charAt(k)));
        }

        list.sort((x, y) -> x.getCharacter() > y.getCharacter() ? 1 : x.getCharacter().equals(y.getCharacter()) ? 0 : -1);

        return list;
    }

    public static class Letter {
        private int index;
        private Character character;

        public Letter(int index, Character character) {
            this.index = index;
            this.character = character;
        }

        public int getIndex() {
            return index;
        }

        public Character getCharacter() {
            return character;
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println(sherlockAndAnagrams("ifailuhkqq"));
        System.out.println(sherlockAndAnagrams("kkkk"));
    }
}
