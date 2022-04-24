package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/binary-gap/
 *
 * @Author jmc
 * @Description
 * @Date 2022/4/24 14:47
 **/
public class Problem_0866_BinaryGap {
    public static int binaryGap(int n) {
        // 22 10110
        // 8  1000
        String str = Integer.toBinaryString(n);
        // 滑动窗口
        int left = 0;
        int right = 0;
        int window = 0;
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                left = i;
                right = right == 0 ? i : right;
                while (window < str.length() - 1 && right + 1 < str.length()) {
                    if (str.charAt(window + 1) == '1') {
                        window++;
                        right = window;
                        break;
                    }
                    window++;
                }
                ans = Math.max(ans, right - left);
            }
        }
        return ans;
    }

    public static int binaryGap2(int n) {
        // 22 10110
        // 11 1011
        int ans = 0;
        int last = -1;
        for (int i = 0; n != 0; i++) {
            if ((n & 1) != 0) {
                if (last != -1) {
                    ans = Math.max(ans, i - last);
                }
                last = i;
            }
            n >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 17;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(n & 1);
        System.out.println(n >> 1);
        System.out.println(binaryGap2(n));
    }
}
