package com.company;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hw4 {
    static int SIZE;
    static int DOTS_TO_WIN;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        do {
            System.out.println("Введите размер поля: ");
            SIZE = sc.nextInt();
        } while (SIZE < 1);

        do {
            System.out.println("Введите кол-во фишек подряд для победы: ");
            DOTS_TO_WIN = sc.nextInt();
        } while (DOTS_TO_WIN < 1);

        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if(checkWin(DOT_X)){
                System.out.println("Ты Супер победитель!");
                break;
            }

            if(isFull()){
                System.out.println("Ничья...");
                break;
            }

            aiTurn();
            printMap();
            if(checkWin(DOT_O)){
                System.out.println("ИИ нынче очень развито, компьютер победил!");
                break;
            }
            if(isFull()){
                System.out.println("Ничья...");
                break;
            }
        }
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%s ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("input koord X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;

    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        //check if ai win possible
        if (winIfPossible(DOT_O, DOT_O))
            return;

        //check if user win possible
        if (winIfPossible(DOT_X, DOT_O))
            return;

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;

    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean winIfPossible(char check, char set) {
        char[][] tmp = new char[SIZE][SIZE];
        copyMap(map, tmp);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = check;
                    if (checkWin(check)) {
                        copyMap(tmp, map);
                        map[i][j] = set;
                        return true;
                    }
                    copyMap(tmp, map);
                }
            }
        }
        return false;
    }

    public static void copyMap(char[][] from, char[][] to) {
        for (int i = 0; i < SIZE; i++) {
            to[i] = Arrays.copyOf(from[i], SIZE);
        }
    }

    public static boolean checkWin(char c){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chekVCnt(i, j, c)) return true;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chekHCnt(i, j, c)) return true;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chekDiagCnt(i, j, c)) return true;
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (chekDiag2Cnt(i, j, c)) return true;
            }
        }
        return false;
    }

    public static boolean chekVCnt(int i, int j, char c) {
        int cnt = 0;
        int k = j;
        while (k < SIZE && map[i][k] == c) {
            cnt++;
            k++;
        }
        return cnt==DOTS_TO_WIN;
    }

    public static boolean chekHCnt(int i, int j, char c) {
        int cnt = 0;
        int k = i;
        while (k < SIZE && map[k][j] == c) {
            cnt++;
            k++;
        }
        return cnt==DOTS_TO_WIN;
    }

    public static boolean chekDiagCnt(int i, int j, char c) {
        int cnt = 0;
        int k = i;
        int m = j;
        while (k < SIZE && m < SIZE && map[k][m] == c) {
            cnt++;
            k++;
            m++;
        }
        return cnt==DOTS_TO_WIN;
    }

    public static boolean chekDiag2Cnt(int i, int j, char c) {
        int cnt = 0;
        int k = i;
        int m = j;
        while (k >=0 && k < SIZE && m < SIZE && map[k][m] == c) {
            cnt++;
            k--;
            m++;
        }
        return cnt==DOTS_TO_WIN;
    }
}
