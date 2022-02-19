package com.jmc.algorithm.jjb.class19;

/**
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/4 17:58
 */
public class Code02_ConvertToLetterString {
    public static int number(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return process(s.toCharArray(), 0);
    }

    private static int process(char[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }

        if (arr[index] == '0') {
            return 0;
        }

        int res = process(arr, index + 1);
        if (index + 1 < arr.length) {
            if (arr[index] == '1' || (arr[index] == '2' && Integer.parseInt(String.valueOf(arr[index + 1])) <= 6)) {
                res += process(arr, index + 2);
            }
        }

        return res;
    }

    public static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] arr = s.toCharArray();
        int N = arr.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int index = N - 1; index >= 0; index--) {
            int res = 0;
            if (arr[index] != '0') {
                res = dp[index + 1];
                if (index + 1 < N) {
                    if (arr[index] == '1' || (arr[index] == '2' && Integer.parseInt(String.valueOf(arr[index + 1])) <= 6)) {
                        res += dp[index + 2];
                    }
                }
            }
            dp[index] = res;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        String str = "2132082";
        System.out.println(number(str));
        System.out.println(dpWays(str));
    }
}
