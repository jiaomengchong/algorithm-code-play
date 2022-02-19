package com.jmc.algorithm.greatOffer.class30;

/**
 * 解码方法II
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/12 19:09
 */
public class Problem_0639_DecodeWaysII {
    public static long MOD = 1000000000 + 7;

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        return (int) ways0(s.toCharArray(), 0);
    }

    private static long ways0(char[] chars, int index) {
        if (chars.length == index) {
            return 1;
        }

        if (chars[index] == '0') {
            return 0;
        }

        if (chars[index] != '*') {
            long ways = ways0(chars, index + 1);
            if (index + 1 == chars.length) {
                ways %= MOD;
                return ways;
            }
            if (chars[index + 1] != '*') {
                int merge = (chars[index] - '0') * 10 + chars[index + 1] - '0';
                if (merge < 27) {
                    ways += ways0(chars, index + 2);
                }
            } else {
                // index+1是'*'
                // 1* -> 11 12 13 14 15 16 17 18 19
                // 2* -> 21 22 23 24 25 26
                ways += (chars[index] == '1' ? 9 : (chars[index] == '2' ? 6 : 0)) * ways0(chars, index + 2);
            }
            ways %= MOD;
            return ways;
        } else {
            // index是'*', 单转
            long ways = 9 * ways0(chars, index + 1);
            if (index + 1 == chars.length) {
                ways %= MOD;
                return ways;
            }

            // index与index+1合并转,分index+1是'*'和不是'*'讨论
            if (chars[index + 1] != '*') {
                // 11 21
                // 12 22
                // 13 23
                // 14 24
                // 15 25
                // 16 26
                // 17
                // 18
                // 19
                ways += (chars[index + 1] < '7' ? 2 : 1) * ways0(chars, index + 2);
            } else {
                // index和index+1都是'*'
                // **组合共15种
                // 11 ~ 19
                // 21 ~ 26
                ways += 15 * ways0(chars, index + 2);
            }

            ways %= MOD;
            return ways;
        }
    }

    public static int numDecodingsDp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int N = chars.length;
        long[] dp = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = -1;
        }

        return (int) waysDp(chars, 0, dp);
    }

    private static long waysDp(char[] chars, int index, long[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }

        if (index == chars.length) {
            return 1;
        }

        if (chars[index] == '0') {
            return 0;
        }

        long ways = 0;
        if (chars[index] != '*') {
            // 单转
            ways += waysDp(chars, index + 1, dp);
            if (index + 1 != chars.length) {
                // 组合
                // index+1是*和不是*
                if (chars[index + 1] != '*') {
                    int merge = (chars[index] - '0') * 10 + chars[index + 1] - '0';
                    if (merge < 27) {
                        ways += waysDp(chars, index + 2, dp);
                    }
                } else {
                    // index+1是*,1*有9种,2*有6种
                    if (chars[index] == '1' || chars[index] == '2') {
                        ways += (chars[index] == '1' ? 9 : 6) * waysDp(chars, index + 2, dp);
                    }
                }
            }
        } else {
            // index是*,单转情况
            ways += 9 * waysDp(chars, index + 1, dp);
            if (index + 1 != chars.length) {
                // 组合转,分情况讨论index+1是*和不是*
                if (chars[index + 1] != '*') {
                    // *0 10 20
                    // *1 11 21
                    // *2 12 22
                    // *3 13 23
                    // *4 14 24
                    // *5 15 25
                    // *6 16 26
                    // *7 17
                    // *8 18
                    // *9 19
                    ways += (chars[index + 1] >= '7' ? 1 : 2) * waysDp(chars, index + 2, dp);
                } else {
                    // ** -> 11~19和21~26共15种
                    ways += 15 * waysDp(chars, index + 2, dp);

                }
            }
        }

        ways %= MOD;
        dp[index] = ways;
        return ways;
    }

    public static int numDecodingsDpBest(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int N = s.length();
        char[] chars = s.toCharArray();
        long[] dp = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = -1;
        }

        dp[N] = 1;
        for (int index = N - 1; index >= 0; index--) {
            if (chars[index] == '0') {
                dp[index] = 0;
                continue;
            }

            long ways = 0;
            // index不是*
            if (chars[index] != '*') {
                // index不是*单转
                ways += dp[index + 1];
                // 合并转,index+1是*和不是*讨论
                if (index + 1 != N) {
                    if (chars[index + 1] != '*') {
                        int merge = (chars[index] - '0') * 10 + chars[index + 1] - '0';
                        if (merge < 27) {
                            ways += dp[index + 2];
                        }
                    } else {
                        // index+1是*
                        if (chars[index] == '1' || chars[index] == '2') {
                            ways += (chars[index] == '1' ? 9 : 6) * dp[index + 2];
                        }
                    }
                }
            } else {
                // index是*,单转
                ways += 9 * dp[index + 1];

                // 合并转
                if (index + 1 != N) {
                    if (chars[index + 1] != '*') {
                        // *0 10 20
                        // *7 17
                        ways += (chars[index + 1] < '7' ? 2 : 1) * dp[index + 2];
                    } else {
                        // **
                        ways += 15 * dp[index + 2];
                    }
                }
            }
            ways %= MOD;
            dp[index] = ways;
        }
        return (int) dp[0];
    }

    public static void main(String[] args) {
        String s = "*10*1***911*";
        System.out.println(numDecodingsDp(s));
        System.out.println(numDecodings(s));
        System.out.println(numDecodingsDpBest(s));
    }
}
