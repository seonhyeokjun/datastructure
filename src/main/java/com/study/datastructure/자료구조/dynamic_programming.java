package com.study.datastructure.자료구조;

import java.awt.*;
import java.util.ArrayList;

public class dynamic_programming {
    /**
     * 최소비용으로 계단오르기
     */
    private static int minCostClimbingStairs(int[] cost){
        int case1 = 0, case2 = 0, current;
        for (int i = cost.length - 1; i >= 0; --i){
            current = cost[i] + Math.min(case1, case2);
            case2 = case1;
            case1 = current;
        }
        return Math.min(case1, case2);
    }

    /**
     * Robot in a Grid
     */
    static class Point{
        int row, col;
        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    static class Solution{
        public ArrayList<Point> findPath(boolean[][] grid){
            if (grid == null || grid.length == 0) return null;
            ArrayList<Point> path = new ArrayList<Point>();
            if (findPath(grid, grid.length - 1, grid[0].length - 1, path)){
                return path;
            } else {
                return null;
            }
        }
        private boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path){
            if (!isInRange(grid, row, col) || grid[row][col]) return false;
            if ((row == 0 && col == 0) || findPath(grid, row, col - 1, path) || findPath(grid, row - 1, col, path)){
                Point p = new Point(row, col);
                path.add(p);
                return true;
            }
            return false;
        }
        private boolean isInRange(boolean[][] grid, int row, int col){
            return row >= 0 && row <= grid.length - 1 && col >= 0 && col <= grid[row].length - 1;
        }
    }

    public static void main(String[] args) {
        int[] cost = new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int result = minCostClimbingStairs(cost);
        System.out.println(result);

        boolean[][] grid = {
                {false, false, false, false},
                {false, false, false, true},
                {true, true, false, false},
                {false, false, false, false}
        };
        Solution sol = new Solution();
        ArrayList<Point> path = sol.findPath(grid);
        if (path != null){
            for (Point p : path){
                System.out.print(p.row + "" + p.col + " -> ");
            }
        }
    }
}
