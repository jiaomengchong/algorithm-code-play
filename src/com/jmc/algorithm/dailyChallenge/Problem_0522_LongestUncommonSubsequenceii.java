package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/longest-uncommon-subsequence-ii/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/27 15:03
 **/
public class Problem_0522_LongestUncommonSubsequenceii {
    public static int findLUSlength(String[] strs) {
        // 输入: strs = ["aaa","aaa","aa"]
        // 输出: 3
        int N = strs.length;
        int ans = -1;
        for (int i = 0; i < N; i++) {
            boolean check = true;
            for (int j = 0; j < N; j++) {
                if (i != j && isLUS(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    private static boolean isLUS(String str1, String str2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < str1.length() && index2 < str2.length()) {
            if (str1.charAt(index1) == str2.charAt(index2)) {
                index1++;
            }
            index2++;
        }
        return index1 == str1.length();
    }
}
