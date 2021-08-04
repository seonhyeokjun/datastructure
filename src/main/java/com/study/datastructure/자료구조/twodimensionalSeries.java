package com.study.datastructure.자료구조;

public class twodimensionalSeries {
    /**
     * 이차원배열에서 0의 행과 열을 모두 0으로 바꾸기
     */
    private static void setZeroToAllZero(int[][] matrix){
        int fc = -1;
        int fr = -1;
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[row].length; col++){
                if (matrix[row][col] == 0){
                    if (fc == -1){
                        fc = col;
                        fr = row;
                    }
                    matrix[fr][col] = 0;
                    matrix[row][fc] = 0;
                }
            }
        }
        if (fc == -1) return;
        for (int col = 0; col < matrix[0].length; col++){
            if (matrix[fr][col] == 0 && col != fc){
                setColsToZero(col, matrix);
            }
        }
        for (int row = 0; row < matrix.length; row++){
            if (matrix[row][fc] == 0){
                setRowsToZero(row, matrix);
            }
        }
        setColsToZero(fc, matrix);
    }
    private static void setColsToZero(int col, int[][] matrix) {
        for (int row = 0; row < matrix.length; row++){
            matrix[row][col] = 0;
        }
    }
    private static void setRowsToZero(int row, int[][] matrix){
        for (int col = 0; col < matrix[row].length; col++){
            matrix[row][col] = 0;
        }
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
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 0},
                {1, 0, 1, 1}
        };
        printImage(matrix);
        setZeroToAllZero(matrix);
        printImage(matrix);
    }
}
