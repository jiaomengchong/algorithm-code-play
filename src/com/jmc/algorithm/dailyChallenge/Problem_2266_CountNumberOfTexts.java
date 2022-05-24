package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/count-number-of-texts/
 */
public class Problem_2266_CountNumberOfTexts {
    private static int MOD = (int) (Math.pow(10, 9) + 7);

    public static int countTexts(String pressedKeys) {
        int[] phones = new int[]{3, 3, 3, 3, 3, 4, 3, 4};
        long[] dp = new long[pressedKeys.length()];
        return process(pressedKeys.toCharArray(), phones, 0, dp);
    }

    private static int process(char[] keys, int[] phones, int index, long[] dp) {
        if (index == keys.length) {
            return 1;
        }


        if (dp[index] != 0) {
            return (int) dp[index];
        }

        if (phones[keys[index] - '2'] == 4) {
            long p1 = process(keys, phones, index + 1, dp);
            if (index + 1 < keys.length && keys[index + 1] == keys[index]) {
                long p2 = process(keys, phones, index + 2, dp);
                p1 += p2;
                if (index + 2 < keys.length && keys[index + 2] == keys[index]) {
                    p1 += process(keys, phones, index + 3, dp);
                    if (index + 3 < keys.length && keys[index + 3] == keys[index]) {
                        p1 += process(keys, phones, index + 4, dp);
                    }
                }
            }
            p1 %= MOD;
            dp[index] = p1;
            return (int) p1;
        } else {
            long p1 = process(keys, phones, index + 1, dp);
            if (index + 1 < keys.length && keys[index + 1] == keys[index]) {
                p1 += process(keys, phones, index + 2, dp);
                if (index + 2 < keys.length && keys[index + 2] == keys[index]) {
                    p1 += process(keys, phones, index + 3, dp);
                }
            }
            p1 %= MOD;
            dp[index] = p1;
            return (int) p1;
        }
    }

    public static void main(String[] args) {
        // 输入：pressedKeys = "22233"
        // 输出：8
        System.out.println(MOD);
        String pressedKeys = "444444444444444444444444444444448888888888888888999999999999333333333333333366666666666666662222222222222222666666666666666633333333333333338888888888888888222222222222222244444444444444448888888888888222222222222222288888888888889999999999999999333333333444444664";
        System.out.println(countTexts(pressedKeys));
    }
}
