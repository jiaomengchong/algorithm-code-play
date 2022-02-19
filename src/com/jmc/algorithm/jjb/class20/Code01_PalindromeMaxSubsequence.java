package com.jmc.algorithm.jjb.class20;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/7 16:16
 */
public class Code01_PalindromeMaxSubsequence {
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = revert(str1);
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    private static int process(char[] str1, char[] str2, int index1, int index2) {
        if (index1 == 0 && index2 == 0) {
            return str1[index1] == str2[index2] ? 1 : 0;
        } else {
            if (index1 == 0) {
                return str1[index1] == str2[index2] ? 1 : process(str1, str2, index1, index2 - 1);
            } else if (index2 == 0) {
                return str1[index1] == str2[index2] ? 1 : process(str1, str2, index1 - 1, index2);
            } else {
                int p1 = process(str1, str2, index1 - 1, index2);
                int p2 = process(str1, str2, index1, index2 - 1);
                int p3 = str1[index1] == str2[index2] ? (1 + process(str1, str2, index1 - 1, index2 - 1)) : 0;
                return Math.max(p1, Math.max(p2, p3));
            }
        }
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = revert(str1);
        int N = str1.length;
        int[][] dp = new int[N][N];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;

        for (int index2 = 1; index2 < N; index2++) {
            dp[0][index2] = (str1[0] == str2[index2]) ? 1 : dp[0][index2 - 1];
        }

        for (int index1 = 1; index1 < N; index1++) {
            dp[index1][0] = str1[index1] == str2[0] ? 1 : dp[index1 - 1][0];
        }

        for (int index1 = 1; index1 < N; index1++) {
            for (int index2 = 1; index2 < N; index2++) {
                int p1 = dp[index1 - 1][index2];
                int p2 = dp[index1][index2 - 1];
                int p3 = str1[index1] == str2[index2] ? (1 + dp[index1 - 1][index2 - 1]) : 0;
                dp[index1][index2] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[N - 1][N - 1];
    }

    private static char[] revert(char[] str1) {
        int N = str1.length;
        char[] revert = new char[N];
        for (int i = 0; i < N; i++) {
            revert[N - 1 - i] = str1[i];
        }

        return revert;
    }

    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;

        return process1(str, 0, N - 1);
    }

    private static int process1(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }

        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }

        int p1 = process1(str, L + 1, R);
        int p2 = process1(str, L, R - 1);
        int p3 = str[L] == str[R] ? (2 + process1(str, L + 1, R - 1)) : 0;
        int p4 = process1(str, L + 1, R - 1);

        return Math.max(Math.max(p1, p4), Math.max(p2, p3));
    }

    public static int longestPalindromeSubseq4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        for (int L = N - 3; L >= 0 ; L--) {
            for (int R = L + 2; R < N; R++) {
                if (R > L) {
                    int p1 = dp[L + 1][R];
                    int p2 = dp[L][R - 1];
                    int p3 = str[L] == str[R] ? (2 + dp[L + 1][R - 1]) : 0;
                    int p4 = dp[L + 1][R - 1];
                    dp[L][R] = Math.max(Math.max(p1, p4), Math.max(p2, p3));
                }
            }
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String str = "\"euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew\"";
//        System.out.println(Arrays.toString(revert(str.toCharArray())));
//        String str = "1abad";
//        System.out.println(longestPalindromeSubseq2(str));
        long start = System.currentTimeMillis();
        System.out.println(longestPalindromeSubseq2(str));
        System.out.println(longestPalindromeSubseq4(str));
        System.out.println("花费时间：" + (System.currentTimeMillis() - start));
    }
}
