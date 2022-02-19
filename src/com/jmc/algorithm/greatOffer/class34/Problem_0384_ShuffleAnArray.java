package com.jmc.algorithm.greatOffer.class34;

import java.util.Arrays;

/**
 * 打乱数组
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/4 11:29
 **/
public class Problem_0384_ShuffleAnArray {
    public static class Solution {
        private int[] origin;
        private int[] shuffle;
        private int N;

        public Solution(int[] nums) {
            N = nums.length;
            origin = nums;
            shuffle = new int[N];
            for (int i = 0; i < N; i++) {
                shuffle[i] = origin[i];
            }
        }

        public int[] reset() {
            return origin;
        }

        public int[] shuffle() {
            for (int i = N - 1; i > 0; i--) {
                int randomIndex = (int) (Math.random() * i + 1);
                int temp = shuffle[i];
                shuffle[i] = shuffle[randomIndex];
                shuffle[randomIndex] = temp;
            }
            return shuffle;
        }
    }

    public static void main(String[] args) {
        /*
        输入 ["Solution", "shuffle", "reset", "shuffle"]
        [[[1, 2, 3]], [], [], []]
        输出 [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
        */
        int[] nums = new int[]{1, 2, 3};
        Solution solution = new Solution(nums);
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));
        System.out.println(Arrays.toString(solution.shuffle()));
    }
}
