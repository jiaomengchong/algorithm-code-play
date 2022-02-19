package com.jmc.algorithm.greatOffer.class30;

/**
 * 解码方法
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/12 16:38
 */
public class Problem_0091_DecodeWays {
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return ways0(s.toCharArray(), 0);
    }

    private static int ways0(char[] chars, int index) {
        // 来到了终止位置,说明之前走的路可以,这是一种解码方法
        if (index == chars.length) {
            return 1;
        }

        // 来到的位置是0字符开头,不是有效的解码方法
        if (chars[index] == '0') {
            return 0;
        }

        // index位置,单个字符方法数
        int ways = ways0(chars, index + 1);

        if (index + 1 == chars.length) {
            return ways;
        }

        // index与index+1合并的方法数
        int merge = (chars[index] - '0') * 10 + chars[index + 1] - '0';
        if (merge < 27) {
            ways += ways0(chars, index + 2);
        }
        return ways;
    }

    public static int numDecodingsDp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];

//        for (int i = 0; i <= chars.length; i++) {
//            dp[i] = -1;
//        }
        
        return waysDp(chars, 0, dp);
    }

    private static int waysDp(char[] chars, int index, int[] dp) {
        if (dp[index] != 0) {
            return dp[index];
        }

        if (index == chars.length) {
            dp[index] = 1;
            return 1;
        }

        if (chars[index] == '0') {
            dp[index] = 0;
            return 0;
        }

        int ways = waysDp(chars, index + 1, dp);
        if (index + 1 == chars.length) {
            dp[index] = ways;
            return ways;
        }

        int merge = (chars[index] - '0') * 10 + chars[index + 1] - '0';
        if (merge < 27) {
            ways += waysDp(chars, index + 2, dp);
        }
        dp[index] = ways;
        return ways;
    }

    public static int numDecodingsDp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 != N) {
                    int merge = (chars[i] - '0') * 10 + (chars[i + 1] - '0');
                    if (i + 2 <= N && merge < 27) {
                        ways += dp[i + 2];
                    }
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String s = "1020170000000007423111111110";
        long start = System.currentTimeMillis();
        System.out.println(numDecodings(s));
        long end1 = System.currentTimeMillis();
        System.out.println("numDecodings 耗时:" + (end1 - start));
        System.out.println(numDecodingsDp(s));
        System.out.println(numDecodingsDp1(s));
        long end2 = System.currentTimeMillis();
        System.out.println("numDecodingsDp 耗时:" + (end2 - end1));

    }
}
