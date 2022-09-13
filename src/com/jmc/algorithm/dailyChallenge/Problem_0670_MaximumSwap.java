package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-swap/
 */
public class Problem_0670_MaximumSwap {
    public static int maximumSwap(int num) {
        int ans = num;
        String copy = String.valueOf(num);
        StringBuffer sb = new StringBuffer(copy);
        int N = copy.length();
        int[][] indexArr = new int[N][2];
        for (int i = 0; i < N; i++) {
            indexArr[i][0] = copy.charAt(i) - '0';
            indexArr[i][1] = i;
        }
        Arrays.sort(indexArr, (a, b) -> b[0] != a[0] ? b[0] - a[0] : b[1] - a[1]);
        for (int i = 0; i < N; i++) {
            if (sb.charAt(i) - '0' != indexArr[i][0]) {
                int curIndex = indexArr[i][1];
                int curI = i;
                while (indexArr[i][0] == sb.charAt(curIndex) - '0' && curI > 0) {
                    curI--;
                    if (indexArr[i][0] == indexArr[curI][0]) {
                        curIndex = indexArr[curI][1];
                    }
                }
                char c = sb.charAt(i);
                char c1 = sb.charAt(curIndex);
                sb.setCharAt(i, c1);
                sb.setCharAt(curIndex, c);
                ans = Integer.valueOf(sb.toString());
                return ans;
            }
        }
        return ans;
    }
}
