package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
 * @Author jmc
 * @Description
 * @Date 2022/5/18 19:30
 **/
public class Problem_0668_KthSmallestNumberInMultiplicationTable {
    public static int findKthNumber(int m, int n, int k) {
        int ans = 0;
        int left = 1, right = m * n;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int count = getCount(mid, m, n);
            if (count >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private static int getCount(int mid, int m, int n) {
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            ans += mid >= i * n ? n : mid / i;
        }
        return ans;
    }

    public static void main(String[] args) {
        int m = 3, n = 3, k = 5;
        System.out.println(findKthNumber(m, n, k));
    }
}
