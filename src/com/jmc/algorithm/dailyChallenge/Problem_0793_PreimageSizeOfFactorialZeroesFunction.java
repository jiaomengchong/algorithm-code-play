package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function/
 */
public class Problem_0793_PreimageSizeOfFactorialZeroesFunction {
    public static int preimageSizeFZF(int k) {
        long start = 0l, end = 5l * k;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long nums = 5l, cnt = 0l;
            while (nums <= mid) {
                cnt += mid / nums;
                nums *= 5;
            }
            if (cnt == k) {
                return 5;
            } else if (cnt < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int k = 1000000000;
        System.out.println(preimageSizeFZF(k));
    }
}
