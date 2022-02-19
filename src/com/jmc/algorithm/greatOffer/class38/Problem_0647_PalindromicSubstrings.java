package com.jmc.algorithm.greatOffer.class38;

/**
 * 回文子串
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/7 18:27
 **/
public class Problem_0647_PalindromicSubstrings {
    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] pArr = manacher(s);
        int ans = 0;
        for (int i = 0; i < pArr.length; i++) {
            ans += pArr[i] >> 1;
        }
        return ans;
    }

    public static int[] manacher(String s) {
        char[] str = s.toCharArray();
        char[] manacherStr = manacherString(str);
        int N = manacherStr.length;
        int[] pArr = new int[N];
        int R = -1;
        int C = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // x  b  b  a  c  a  b  b  d  s  t  s  d  b  b  a  c  a  b  b  d
            // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20
            //    L        i'                C                 i        R
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < N && i - pArr[i] >= 0) {
                if (manacherStr[i + pArr[i]] == manacherStr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
                max = Math.max(max, pArr[i]);
            }
        }
        return pArr;
    }

    private static char[] manacherString(char[] str) {
        int N = str.length * 2 + 1;
        char[] manacherStr = new char[N];
        for (int i = 0; i < N; i++) {
            manacherStr[i] = (i & 1) == 0 ? '#' : str[i / 2];
        }

        return manacherStr;
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(manacherString(s.toCharArray()));
        System.out.println(countSubstrings(s));
    }
}
