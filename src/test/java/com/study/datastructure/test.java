package com.study.datastructure;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class test {

    @Test
    void a(){
        Scanner scan = new Scanner(System.in);
        String b = scan.nextLine();
        String[] arr = b.split("\\.");
        int a = Integer.parseInt(arr[0]);
        int c = Integer.parseInt(arr[1]);
        int d = Integer.parseInt(arr[2]);
        System.out.printf("%04d.%02d.%02d", a, c, d);
    }
}
