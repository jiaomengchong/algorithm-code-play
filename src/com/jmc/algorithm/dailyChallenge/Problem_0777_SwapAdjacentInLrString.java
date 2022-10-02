package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/swap-adjacent-in-lr-string/
 */
public class Problem_0777_SwapAdjacentInLrString {
    public static boolean canTransform(String start, String end) {
        int N = start.length();
        for (int i = 0, j = 0; i < N || j < N; i++, j++) {
            while (i < N && start.charAt(i) == 'X') {
                i++;
            }
            while (j < N && end.charAt(j) == 'X') {
                j++;
            }
            if (i == N && j != N) {
                return false;
            }

            if (j == N && i != N) {
                return false;
            }

            if (i < N && j < N && start.charAt(i) != end.charAt(j)) {
                return false;
            }
            if (i < N && j < N && start.charAt(i) == 'L' && i < j) {
                return false;
            }
            if (i < N && j < N && start.charAt(i) == 'R' && i > j) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //"RXXLRXRXL"
        //"XRLXXRRLX"
        String start = "RXXLRXRXL", end = "XRLXXRRLX";
        System.out.println(canTransform(start, end));
    }
}
