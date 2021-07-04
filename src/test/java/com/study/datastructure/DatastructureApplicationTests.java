package com.study.datastructure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class DatastructureApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void main(){
        Scanner scanner = new Scanner(System.in);
        var test = scanner.nextLine();
        System.out.println(test);
    }

}
