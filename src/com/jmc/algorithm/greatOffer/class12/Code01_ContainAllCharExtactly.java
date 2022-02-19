package com.jmc.algorithm.greatOffer.class12;

/**
 * 字符串排列问题：
 * 给定长度为m的字符串aim，以及一个长度为n的字符串str
 * 问能否在str中找到一个长度为m的连续子串，
 * 使得这个子串刚好由aim的m个字符组成，顺序无所谓，
 * 返回任意满足条件的一个子串的起始位置，未找到返回-1
 * <p>
 * 测试链接：https://leetcode.com/problems/permutation-in-string/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/5 20:16
 */
public class Code01_ContainAllCharExtactly {
    public static int containExactly0(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() < s2.length()) {
            return -1;
        }

        int M = s2.length();
        int all = M;
        int R = 0;
        int[] counts = new int[256];
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        for (int i = 0; i < M; i++) {
            counts[str2[i]]++;
        }
        // 形成初步窗口
        for (; R < M; R++) {
            if (counts[str1[R]]-- > 0) {
                all--;
            }
        }

        for (int L = 0; R < s1.length(); L++, R++) {
            if (all == 0) {
                return R - M;
            }
            // 左出
            if (counts[str1[L]]++ >= 0) {
                all++;
            }
            // 右进
            if (counts[str1[R]]-- > 0) {
                all--;
            }
        }

        return all == 0 ? R - M : -1;
    }

    public static boolean checkInclusion(String s1, String s2) {
        int ans = containExactly0(s2, s1);
        return ans == -1 ? false : true;
    }

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2));
    }
}
