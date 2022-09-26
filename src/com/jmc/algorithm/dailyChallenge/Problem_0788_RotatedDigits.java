package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/rotated-digits/
 */
public class Problem_0788_RotatedDigits {
    public int rotatedDigits(int n) {
        int ans = 0;
        // 0 1 2 3 4 5 6 7 8 9
        int[] base = new int[]{0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
        for (int i = 1; i <= n; i ++) {
            boolean valid = true, diff = false;
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (base[ch - '0'] == -1) {
                    valid = false;
                } else if (base[ch - '0'] == 1) {
                    diff = true;
                }
            }
            if (valid && diff) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 10;
        Problem_0788_RotatedDigits rotatedDigits = new Problem_0788_RotatedDigits();
        System.out.println(rotatedDigits.rotatedDigits(n));
    }
}
