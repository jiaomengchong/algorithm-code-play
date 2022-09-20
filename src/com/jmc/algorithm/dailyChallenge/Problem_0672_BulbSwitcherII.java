package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/bulb-switcher-ii/
 */
public class Problem_0672_BulbSwitcherII {
    public static int flipLights(int n, int presses) {
        int ans = 0;
        // 1 2 3 4 5 6 7 8 9 10 11 12
        //   2   4   6   8   10    12
        // 1   3   5   7   9    11
        // 1     4     7     10

        // 6k+1  1、3、4
        // 6k+2  1、2
        // 6k+3  1、3
        // 6k+4  1、2、4
        if (presses == 0) {
            return 1;
        }

        if (n == 1) {
            return 2;
        } else if (n == 2) {
            return presses == 1 ? 3 : 4;
        } else {
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
    }
}
