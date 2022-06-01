package com.jmc.algorithm.dailyChallenge;

public class Problem_0191_NumberOf1Bits {
    public static int hammingWeight(int n) {
        // 输入：00000000000000000000000000001011
        // 输出：3
        int ans = 0;
        while (n != 0) {
            ans++;
            int rightOne = n & (-n);
            n ^= rightOne;
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 11111111;
        System.out.println(hammingWeight(n));
    }
}
