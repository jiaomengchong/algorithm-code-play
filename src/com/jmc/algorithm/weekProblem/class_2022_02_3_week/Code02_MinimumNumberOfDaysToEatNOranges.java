package com.jmc.algorithm.weekProblem.class_2022_02_3_week;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-eat-n-oranges/
 */
public class Code02_MinimumNumberOfDaysToEatNOranges {
    public static int minDays(int n) {
        if (n <= 1) {
            return 1;
        }

        // 第一种情况
        int p1 = n;
        int p2 = n % 2 == 0 ? 1 + minDays(n / 2) : 2 + minDays(n / 2);
        int p3 = n % 3 == 0 ? 1 + minDays(n / 3) : (n % 3) + 1 + minDays(n / 3);
        return Math.min(p1, Math.min(p2, p3));
    }

    // 记忆化搜索
    public static int minDaysDp(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        return process(n, dp);
    }

    private static int process(int n, Map<Integer, Integer> dp) {
        if (n <= 1) {
            return 1;
        }

        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        int p1 = n % 2 == 0 ? 1 + process(n / 2, dp) : 2 + process(n / 2, dp);
        int p2 = n % 3 == 0 ? 1 + process(n / 3, dp) : (n % 3) + 1 + process(n / 3, dp);
        int ans = Math.min(p1, p2);
        dp.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        int n = 1943030610;
        System.out.println(minDays(n));
        System.out.println(minDaysDp(n));
    }
}
