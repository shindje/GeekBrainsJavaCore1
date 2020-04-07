package com.company;

public class Hw1 {

    static int calculate(int a, int b, int c, int d) {
        return a*(b+(c/d));
    }

    static boolean checkSumm(int a, int b) {
        return (a+b)>=10 && (a+b)<=20;
    }

    static void printSign(int a) {
        if (a>=0)
            System.out.println("Число положительное");
        else
            System.out.println("Число отрицательное");
    }

    static boolean isNegative(int a) {
        return a<0;
    }

    static void printHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    static void checkYear(int year) {
        if (year%4 == 0 && (year%100 > 0 || year%400 ==0))
            System.out.println("Год високосный");
        else
            System.out.println("Год не високосный");
    }

    public static void main(String[] args) {
        byte bt = 2;
        short sh = 45;
        int i = 356;
        long l = 3566;
        float f = 345.556f;
        double d = 555.22;
        char c = 'S';
        boolean b = false;
        String s = "some";

        System.out.println(calculate(3,4,10,5));
        System.out.println(checkSumm(7,8));
        printSign(-2);
        System.out.println(isNegative(4));
        printHello("Вася");
        checkYear(1900);
    }
}