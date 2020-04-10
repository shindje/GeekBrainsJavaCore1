package com.company;

import java.util.Arrays;

public class Hw2 {

    public static boolean checkBalance(int[] a) {
        for (float idx = 0.5f; idx < a.length-1; idx++) {
            int sumLeft = 0;
            int sumRight = 0;
            for (int j = 0; j < idx; j++) {
                sumLeft += a[j];
            }
            for (int j = (int)(idx + 0.5); j < a.length; j++) {
                sumRight += a[j];
            }
            if (sumLeft == sumRight)
                return true;
        }
        return false;
    }

    public static void move(int[] a, int n) {
        boolean right = (n > 0);
        for (int i = 1; i <= (right? n: -n); i++) {
            moveOneStep(a, right);
        }
    }

    //Смещение на 1 позицию
    public static void moveOneStep(int[] a, boolean right) {
        for (int i = 0; i < a.length - 1; i++) {
            if (right)
                swap(a, a.length - i - 1, (a.length - i >= a.length)? 0: a.length - i);
            else
                swap(a, i, (i-1 < 0)? a.length-1: i-1);

        }
    }

    public static void swap(int[] a, int i1, int i2) {
        int tmp = a[i1];
        a[i1] = a[i2];
        a[i2] = tmp;
    }

    public static void main(String[] args) {
        //1
        int[] a1 = {1,0,1,0,1,1,0,1,1,0,1};
        System.out.print("1: " + Arrays.toString(a1));
        for (int i = 0; i < a1.length; i++)
            if (a1[i] == 0)
                a1[i] = 1;
            else
                a1[i] = 0;
        System.out.println(" -> " + Arrays.toString(a1));

        //2
        int[] a2 = new int[8];
        for (int i = 0; i < a2.length; i++)
            a2[i] = i*3;
        System.out.println("2: " + Arrays.toString(a2));

        //3
        int[] a3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < a3.length; i++) {
            if (a3[i] < 6)
                a3[i] = a3[i]*2;
        }
        System.out.println("3: " + Arrays.toString(a3));

        //4
        int[][] a4 = new int[5][5];
        for (int i = 0; i < a4.length; i++) {
            a4[i][i] = 1;
            a4[i][a4.length-i-1] = 1;
        }
        System.out.println("4:");
        for (int i = 0; i < a4.length; i++) {
            System.out.println(Arrays.toString(a4[i]));
        }

        //5
        int[] a5 = {54,-336,3,-6,165,-64,57,4,7,-3,534,-53,4,363,7};
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a5.length; i++) {
            if (a5[i] < min)
                min = a5[i];
            if (a5[i] > max)
                max = a5[i];
        }
        System.out.println("5. " + Arrays.toString(a5) + " min = " + min + " max = " + max);

        //6
        int[] a61 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] a62 = {1, 1, 1, 2, 1};
        int[] a63 = {4, 5, 2, 10};
        System.out.println("6. " + Arrays.toString(a61) + ": "  + checkBalance(a61));
        System.out.println(Arrays.toString(a62) + ": "  + checkBalance(a62));
        System.out.println(Arrays.toString(a63) + ": "  + checkBalance(a63));

        //7
        int[] a71 = {-25, -10, -5, -1, 3, 10, 12, 26, 33, 57, 89, 102, 246, 789};
        System.out.println("7.");
        System.out.println(Arrays.toString(a71) + " move 3 steps right: ");
        move(a71, 3);
        System.out.println(Arrays.toString(a71));
        System.out.println();
        int[] a72 = {-52, -45, -20, -6, 0, 2, 14, 25, 36, 50};
        System.out.println(Arrays.toString(a72) + " move 2 steps left: ");
        move(a72, -2);
        System.out.println(Arrays.toString(a72));
    }
}
