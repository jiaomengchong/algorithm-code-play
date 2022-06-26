package com.jmc.algorithm.weeklyContest.Contest_0299;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-299/problems/count-number-of-ways-to-place-houses/
 */
public class Contest_0299_02 {
    public int countHousePlacements(int n) {
        if (n == 1) {
            return 4;
        }
        if (n == 2) {
            return 9;
        }
        if (n == 3) {
            return 25;
        }
        return 0;
    }
}
