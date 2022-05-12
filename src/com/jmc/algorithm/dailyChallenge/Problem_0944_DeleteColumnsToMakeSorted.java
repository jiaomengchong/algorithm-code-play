package com.jmc.algorithm.dailyChallenge;

/**
 * @Author jmc
 * @Description
 * @Date 2022/5/12 10:35
 **/
public class Problem_0944_DeleteColumnsToMakeSorted {

    public static int minDeletionSize(String[] strs) {

        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return 0;
        }

        int rows = strs.length;
        int cols = strs[0].length();
        int ans = 0;
        for (int i = 0; i < cols; i++) {
            int pre = 0;
            for (int j = 0; j < rows; j++) {
                int cur = strs[j].charAt(i) - 'a';
                if (cur < pre) {
                    ans++;
                    break;
                }
                pre = cur;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"zyx","wvu","tsr"};
        System.out.println(minDeletionSize(strs));
    }
}
