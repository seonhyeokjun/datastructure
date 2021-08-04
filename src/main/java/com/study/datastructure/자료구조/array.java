package com.study.datastructure.자료구조;

import java.util.HashMap;

public class array {
    static class Solution{
        public int[] twoSum(int[] nums, int target){
            for (int i = 0; i < nums.length; i++){
                for (int j = i + 1; j < nums.length; j++){
                    if (target == nums[i] + nums[j]){
                        return new int[] {i, j};
                    }
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
        public int[] twoSum2(int[] nums, int target){
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; i++) map.put(nums[i], i);
            for (int i1 = 0; i1 < nums.length; i1++){
                Integer i2 = map.get(target - nums[i1]);
                if (i2 != null && i1 != i2) return new int[]{i1, i2};
            }
            throw new IllegalArgumentException("No two sum solution");
        }
        public int[] twoSum3(int[] nums, int target){
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; i++){
                if (map.containsKey(target - nums[i])){
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }

    public static void main(String[] args) {
        int[] nums = {6,4,3,8,7,5,2};
        Solution sol = new Solution();
        int[] result = sol.twoSum(nums, 5);
        int[] result2 = sol.twoSum2(nums, 5);
        int[] result3 = sol.twoSum3(nums, 5);
        System.out.println(result[0] + ", " + result[1]);
        System.out.println(result2[0] + ", " + result2[1]);
        System.out.println(result3[0] + ", " + result3[1]);
    }
}
