package com.company;

import java.util.Random;
import java.util.Scanner;

public class Hw3Words {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                          "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                          "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String toGuess = words[new Random().nextInt(words.length)];
        String input;
        System.out.println("Загадано слово. Попробуйте отгадать");
        do {
            input = sc.nextLine();
            if (toGuess.equals(input))
                System.out.println("Вы угадали!");
            else

                System.out.println("Уагаданы буквы: " + compareWords(input, toGuess, '#', 15));
        } while (!toGuess.equals(input));
    }

    static String compareWords(String w1, String w2, char maskChar, int maskLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maskLength; i++) {
            if (i < w1.length() && i < w2.length() && w1.charAt(i) == w2.charAt(i))
                sb.append(w1.charAt(i));
            else
                sb.append(maskChar);
        }
        return sb.toString();
    }
}
