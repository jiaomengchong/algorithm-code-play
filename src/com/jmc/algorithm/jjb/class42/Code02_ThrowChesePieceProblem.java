package com.jmc.algorithm.jjb.class42;

/**
 * leetcode测试链接：https://leetcode.com/problems/super-egg-drop
 * K蛋问题、扔棋子问题
 *
 * @author jmc
 * @version 1.0
 * @date 2021/3/30 18:02
 */
public class Code02_ThrowChesePieceProblem {
    public static int superEggDrop_2(int kChess, int nLevel) {
        if (kChess < 1 || nLevel < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }

        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i <= nLevel; i++) {
            dp[i][1] = i;
        }

        for (int i = 1; i <= nLevel; i++) {
            for (int j = 2; j <= kChess; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    ans = Math.min(ans, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = ans + 1;
            }
        }
        return dp[nLevel][kChess];
    }
    
    public static int superEggDrop_3(int kChess, int nLevel) {
        if (kChess < 1 || nLevel < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        
        int[][] dp = new int[nLevel + 1][kChess + 1];
        int[][] best = new int[nLevel + 1][kChess + 1];
        for (int i = 0; i <= nLevel; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i <= kChess; i++) {
            dp[1][i] = 1;
            best[1][i] = 1;
        }

        for (int i = 2; i <= nLevel; i++) {
            for (int j = kChess; j > 1; j--) {
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                int down = best[i - 1][j];
                int up = j == kChess ? i : best[i][j + 1];
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    int leftCost = dp[leftEnd - 1][j - 1];
                    int rightCost = dp[i - leftEnd][j];
                    int cur = Math.max(leftCost, rightCost);
                    if (cur <= ans) {
                        ans = cur;
                        choose = leftEnd;
                    }
                    dp[i][j] = ans + 1;
                    best[i][j] = choose;
                }
            }
        }
        return dp[nLevel][kChess];
    }

    public static int superEggDrop_4(int kChess, int nLevel) {
        if (kChess < 1 || nLevel < 1) {
            return 0;
        }

        int[] dp = new int[kChess];
        int ans = 0;
        while (true) {
            ans++;
            int pre = 0;
            for (int i = 0; i < dp.length; i++) {
                int temp = dp[i];
                dp[i] += pre + 1;
                pre = temp;
                if (dp[i] >= nLevel) {
                    return ans;
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(superEggDrop_2(2, 105));
        System.out.println(superEggDrop_3(2, 105));
        System.out.println(superEggDrop_4(2, 105));
    }
}
