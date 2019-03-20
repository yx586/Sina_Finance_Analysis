package com.java.test;

import java.util.Scanner;

public class Odd {

    public static void isOdd(int i) {
        if (i % 2 == 1) {
            System.out.println(i + " is odd");
        } else {
            System.out.println(i + " is even");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        Odd.isOdd(i);
    }
}
