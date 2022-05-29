package com.jmc.algorithm.weeklyContest.contest_0295;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-295/problems/rearrange-characters-to-make-target-string/
 */
public class Contest_0295_01 {
    public static int rearrangeCharacters(String s, String target) {
        // 输入：s = "ilovecodingonleetcode", target = "code"
        // 输出：2
        int[] numS = new int[26];
        int[] numT = new int[26];
        for (int i = 0; i < s.length(); i++) {
            numS[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < target.length(); i++) {
            numT[target.charAt(i) - 'a']++;
        }

        int ans = 0;
        while (true) {
            boolean[] check = new boolean[26];
            for (int i = 0; i < 26; i++) {
                if (numT[i] != 0) {
                    if (numS[i] < numT[i]) {
                        return ans;
                    }
                    numS[i] -= numT[i];
                    check[i] = true;
                } else {
                    check[i] = true;
                }
            }

            boolean all = true;
            for (int i = 0; i < 26; i++) {
                if (!check[i]) {
                    all = false;
                }
            }
            if (all) {
                ans++;
            }
        }
    }

    public static void main(String[] args) {
        String s = "abcbacddd";
        String target = "abc";
        System.out.println(rearrangeCharacters(s, target));
    }
}
