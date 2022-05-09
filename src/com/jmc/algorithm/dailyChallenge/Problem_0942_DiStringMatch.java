package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * @Author jmc
 * @Description
 * @Date 2022/5/9 16:43
 **/
public class Problem_0942_DiStringMatch {

    public static int[] diStringMatch(String s) {
        // 输入：s = "IDID"
        // 输出：[0,4,1,3,2]
        int[] ans = new int[s.length() + 1];
        int low = 0, high = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[s.length()] = low;
        return ans;
    }

    public static void main(String[] args) {
        String s = "DDI";
        System.out.println(Arrays.toString(diStringMatch(s)));
    }
}
