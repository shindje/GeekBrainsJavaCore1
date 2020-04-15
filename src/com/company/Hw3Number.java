package com.company;

import java.util.Random;
import java.util.Scanner;

public class Hw3Number {

    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        int exit;
        do {
            int toGuess = rnd.nextInt(10);
            int tries = 3;
            int input;
            System.out.println("Загадано число от 0 до 9. Попробуйте угадать");
            do {
                input = sc.nextInt();
                if (input == toGuess)
                    System.out.println("Вы угадали!");
                else if (input < toGuess)
                    System.out.println("Загаданное число больше");
                else
                    System.out.println("Загаданное число меньше");
                tries--;
            } while (tries > 0 && toGuess != input);
            if (tries == 0 && toGuess != input)
                System.out.println("Попытки закончились. Вы не угадали! Было загадано - " + toGuess);
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            exit = sc.nextInt();
        } while (exit != 0);
    }
}
