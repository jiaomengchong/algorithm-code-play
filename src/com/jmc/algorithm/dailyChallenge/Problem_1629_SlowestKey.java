package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/slowest-key/
 */
public class Problem_1629_SlowestKey {
    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] counts = new int[26];
        char[] kpChar = keysPressed.toCharArray();
        counts[kpChar[0] - 'a'] = releaseTimes[0];
        for (int i = 1; i < kpChar.length; i++) {
            counts[kpChar[i] - 'a'] = Math.max(counts[kpChar[i] - 'a'], releaseTimes[i] - releaseTimes[i - 1]);
        }
        int ans = 0;
        for (int i = 1; i < 26; i++) {
            if (counts[i] >= counts[ans]) {
                ans = i;
            }
        }
        return (char) ('a' + ans);
    }

    public static void main(String[] args) {
        int[] releaseTimes = new int[]{12,23,36,46,62};
        String keyPressed = "spuda";
        System.out.println(slowestKey(releaseTimes, keyPressed));
    }
}
