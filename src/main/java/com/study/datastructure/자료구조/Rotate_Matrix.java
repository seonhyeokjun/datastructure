package com.study.datastructure.자료구조;

public class Rotate_Matrix {
    /**
     * 이미지 회전 알고리즘 - Rotate Matrix
     */
    private static int[][] rotateImage(int[][] image){
        int tmp;
        for (int s = 0, e = image.length - 1; s < e; s++, e--){
            for (int i = s, j = e; i < e; i++, j--){
                tmp = image[s][i];
                image[s][i] = image[i][e];
                image[i][e] = image[e][j];
                image[e][j] = image[j][s];
                image[j][s] = tmp;
            }
        }
        return image;
    }
    private static void printImage(int[][] image){
        for (int i = 0; i < image.length; i++){
            for (int j = 0; j < image[i].length; j++){
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        printImage(image);
        rotateImage(image);
        printImage(image);
        rotateImage(image);
        printImage(image);
        rotateImage(image);
        printImage(image);
        rotateImage(image);
        printImage(image);
    }
}
