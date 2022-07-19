package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/array-nesting/
 */
public class Problem_0565_ArrayNesting {
    public static int arrayNesting(int[] nums) {
        int N = nums.length;
        int ans = 0;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            int curAns = 0;
            while (!visited[i]) {
                curAns++;
                visited[i] = true;
                i = nums[i];
            }
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

    public static int arrayNesting2(int[] nums) {
        int N = nums.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int curAns = 0;
            while (nums[i] < N) {
                curAns++;
                int tmp = nums[i];
                nums[i] = N;
                i = tmp;
            }
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

    public static void main(String[] args) {
        // [0,1,2]
        int[] nums = new int[]{5,4,0,3,1,6,2};
        System.out.println(arrayNesting(nums));
        System.out.println(arrayNesting2(nums));
    }
}
