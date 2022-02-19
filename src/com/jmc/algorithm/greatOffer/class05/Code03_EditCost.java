package com.jmc.algorithm.greatOffer.class05;

/**
 * 编辑距离
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/10 11:00
 */
public class Code03_EditCost {
    public static int minDistance1(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        return process1(str1, str2, 1, 1, 1);
    }

    private static int process1(char[] str1, char[] str2, int ic, int dc, int rc) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N + 1][M + 1];

        // 第一行str1 -> str2, 插入开销 * 列
        for (int i = 1; i <= M; i++) {
            dp[0][i] = ic * i;
        }

        // 第一列str1 -> str2, 删除开销 * 行
        for (int i = 1; i <= N; i++) {
            dp[i][0] = dc * i;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
            }
        }
        return dp[N][M];
    }

    public static int minDistance2(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        return minCost2(word1, word2, 1, 1, 1);
    }

    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j];
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp;
            }
        }
        return dp[shorts.length];
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance1(word1, word2));
        System.out.println(minDistance2(word1, word2));
    }
}
