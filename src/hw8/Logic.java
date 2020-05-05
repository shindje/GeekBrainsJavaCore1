package hw8;


import java.util.Random;
import java.util.Scanner;

public class Logic {
    static int SIZE_X;
    static int SIZE_Y;
    static int DOTS_TO_WIN;

    static int gameMode;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static final int INVALID_MOVE = -1;
    static final int CONTINUE = 0;
    static final int AI_WINS = 1;
    static final int HUMAN_WINS = 2;
    static final int DRAW = 3;

    static char[][] map;

    static Random random = new Random();
    static boolean gameFinished = false;

    static int[] winLine = new int[4];

    public static int go() {
        gameFinished = true;
            printMap();
            if (checkWinLines(DOT_X)) {
                System.out.println("Ты Супер победитель!");
                return HUMAN_WINS;
            }

            if (isFull()) {
                System.out.println("Ничья...");
                return DRAW;
            }

            if (gameMode == BattleMap.MODE_H_V_A) {
                aiTurn();
                printMap();
                if (checkWinLines(DOT_O)) {
                    System.out.println("ИИ нынче очень развито, компьютер победил!");
                    return AI_WINS;
                }
                if (isFull()) {
                    System.out.println("Ничья...");
                    return DRAW;
                }
            } else {
                if (checkWinLines(DOT_O)) {
                    System.out.println("Ты Супер победитель!");
                    return AI_WINS;
                }
            }
        gameFinished = false;
        return CONTINUE;
    }

    public static void initMap() {
        map = new char[SIZE_X][SIZE_Y];
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE_Y; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE_X; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE_Y; j++) {
                System.out.printf("%s ", map[i][j]);
            }
            System.out.println();
        }
    }


    public static int setHumanXY(int x, int y, char dot){
        if(isCellValid(x, y)){
            map[x][y] = dot;
            return go();
        } else {
            return INVALID_MOVE;
        }
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE_X || y >= SIZE_Y) {
            return false;
        }
        return map[x][y] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWinLines(DOT_O)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }


        do {
            y = random.nextInt(SIZE_X);
            x = random.nextInt(SIZE_Y);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    static boolean checkLine(int cy, int cx, int vy, int vx, char dot) {
        if (cx + vx * DOTS_TO_WIN - 1 > SIZE_Y - 1 || cy + vy * DOTS_TO_WIN - 1 > SIZE_X - 1 ||
                cx + vx * (DOTS_TO_WIN - 1) < 0 || cy + vy * (DOTS_TO_WIN - 1) < 0) {
            return false;
        }

        winLine[0] = cy;
        winLine[1] = cx;

        int i = 0;

        for (i = 0; i < DOTS_TO_WIN; i++) {
            if (map[cy + i * vy][cx + i * vx] != dot) {
                return false;
            }
        }

        winLine[2] = cy + (i-1) * vy;
        winLine[3] = cx + (i-1) * vx;

        return true;
    }

    static boolean checkWinLines(char dot) {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (checkLine(i, j, 0, 1, dot) || checkLine(i, j, 1, 0, dot) ||
                        checkLine(i, j, 1, 1, dot) || checkLine(i, j, -1, 1, dot)) {
                    return true;
                }
            }
        }
        return false;
    }
}
