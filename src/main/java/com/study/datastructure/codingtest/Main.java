package com.study.datastructure.codingtest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String[] b = a.split(":");
        int c = Integer.parseInt(b[1]);
        System.out.println(c);
    }
}
