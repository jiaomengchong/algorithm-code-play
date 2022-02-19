package com.jmc.algorithm.jjb.class46;

/**
 * 如果一个字符相邻的位置没有相同字符，那么这个位置的字符出现不能被消掉
 * 比如:"ab"，其中a和b都不能被消掉
 * 如果一个字符相邻的位置有相同字符，就可以一起消掉
 * 比如:"abbbc"，中间一串的b是可以被消掉的，消除之后剩下"ac"
 * 某些字符如果消掉了，剩下的字符认为重新靠在一起
 * 给定一个字符串，你可以决定每一步消除的顺序，目标是请尽可能多的消掉字符，返回最少的剩余字符数量
 * 比如："aacca", 如果先消掉最左侧的"aa"，那么将剩下"cca"，然后把"cc"消掉，剩下的"a"将无法再消除，返回1
 * 但是如果先消掉中间的"cc"，那么将剩下"aaa"，最后都消掉就一个字符也不剩了，返回0，这才是最优解。
 * 再比如："baaccabb"，
 * 如果先消除最左侧的两个a，剩下"bccabb"，
 * 如果再消除最左侧的两个c，剩下"babb"，
 * 最后消除最右侧的两个b，剩下"ba"无法再消除，返回2
 * 而最优策略是：
 * 如果先消除中间的两个c，剩下"baaabb"，
 * 如果再消除中间的三个a，剩下"bbb"，
 * 最后消除三个b，不留下任何字符，返回0，这才是最优解
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/12 21:01
 */
public class Code03_DeleteAdjacentSameCharacter {
    public static int canDelete0(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        return process0(s.toCharArray(), 0, s.length() - 1, false);
    }

    private static int process0(char[] str, int L, int R, boolean has) {
        if (L > R) {
            return 0;
        }

        if (L == R) {
            return has ? 0 : 1;
        }

        int K = has ? 1 : 0;
        int index = L;
        while (index <= R && str[index] == str[L]) {
            index++;
            K++;
        }
        int ways1 = (K > 1 ? 0 : 1) + process0(str, index, R, false);
        int ways2 = Integer.MAX_VALUE;
        for (int split = index; split <= R; split++) {
            if (str[split] == str[L] && str[split - 1] != str[split]) {
                if (process0(str, index, split - 1, false) == 0) {
                    ways2 = Math.min(ways2, process0(str, split, R, K != 0));
                }
            }
        }
        return Math.min(ways1, ways2);
    }

    public static int canDeleteDp(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        char[] str = s.toCharArray();
        int N = str.length;
        int[][][] dp = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return processDp(str, 0, N - 1, false, dp);
    }

    private static int processDp(char[] str, int L, int R, boolean has, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return has ? 0 : 1;
        }

        int K = has ? 1 : 0;
        if (dp[L][R][K] != -1) {
            return dp[L][R][K];
        }

        int index = L;
        int all = K;
        while (index <= R && str[index] == str[L]) {
            index++;
            all++;
        }
        int ans1 = (all > 1 ? 0 : 1) + processDp(str, index, R, false, dp);

        int ans2 = Integer.MAX_VALUE;
        for (int split = index; split <= R; split++) {
            if (str[split] == str[L] && str[split - 1] != str[split]) {
                if (processDp(str, index, split - 1, false, dp) == 0) {
                    ans2 = Math.min(ans2, processDp(str, split, R, all != 0, dp));
                }
            }
        }

        dp[L][R][K] = Math.min(ans1, ans2);
        return Math.min(ans1, ans2);
    }

    public static void main(String[] args) {
        String s = "caaccbbcbabbcba";
        System.out.println(canDelete0(s));
        System.out.println(canDeleteDp(s));
    }
}
