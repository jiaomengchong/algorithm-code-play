package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/solve-the-equation/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/11 10:15
 **/
public class Problem_640_SolveTheEquation {
    public static String solveEquation(String equation) {
        String[] arr = equation.split("=");
        int[] left = countXAndNum(arr[0]);
        int[] right = countXAndNum(arr[1]);

        if (left[0] == right[0] && left[1] == right[1]) {
            return "Infinite solutions";
        }

        int remainingX = left[0] - right[0];
        int remainingNum = right[1] - left[1];

        if (remainingX == 0) {
            return "No solution";
        }
        return String.format("x=%s", remainingNum / remainingX);
    }

    private static int[] countXAndNum(String s) {
        int symbol = 1;
        int i = 0, N = s.length(), xCnt = 0, num = 0;
        while (i < N) {
            boolean isCoe = false;
            int coefficient = 0;
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                symbol = s.charAt(i) == '+' ? 1 : -1;
                i++;
            }

            while (i < N && Character.isDigit(s.charAt(i))) {
                coefficient = coefficient * 10 + (s.charAt(i) - '0');
                isCoe = true;
                i++;
            }

            if (i < N && s.charAt(i) == 'x') {
                xCnt += isCoe ? coefficient * symbol : symbol;
                i++;
            } else {
                num += coefficient * symbol;
            }
        }
        return new int[]{xCnt, num};
    }

    public static void main(String[] args) {
        String equation = "x=x+2";
        System.out.println(solveEquation(equation));
    }
}
