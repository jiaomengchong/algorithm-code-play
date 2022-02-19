package com.jmc.algorithm.greatOffer.class34;

/**
 * @Author jmc
 * @Description
 * @Date 2021/9/2 15:18
 **/
public class Problem_0340_LongestSubstringWithAtMostKDistanceCharacters {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k < 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int N = str.length;
        int[] count = new int[256];
        int R = 0;
        int ans = 0;
        int diff = 0;

        for (int i = 0; i < N; i++) {
            while (R < N && (diff < k || (k == diff && count[str[R]] > 0))) {
                diff += count[str[R]] == 0 ? 1 : 0;
                count[str[R++]]++;
            }
            ans = Math.max(ans, R - i);
            diff -= count[str[i]] == 1 ? 1 : 0;
            count[str[i]]--;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "eceba";
        int k = 3;
        System.out.println(lengthOfLongestSubstringKDistinct(s, k));
    }
}
