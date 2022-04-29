package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/plus-one/
 */
public class Problem_0066_PlusOne {
    public static int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[]{};
        }

        int N = digits.length;
        int[] ans = new int[N + 1];
        boolean carry = digits[N - 1] == 9;
        ans[N] = digits[N - 1] == 9 ? 0 : digits[N - 1] + 1;
        for (int i = N - 2; i >= 0; i--) {
            if (digits[i] == 9 & carry) {
                ans[i + 1] = 0;
                carry = true;
            } else {
                ans[i + 1] = carry ? digits[i] + 1 : digits[i];
                carry = false;
            }
        }

        ans[0] = carry ? 1 : 0;

        int[] ansNoCarry = new int[N];
        if (ans[0] == 0) {
            for (int i = 0; i < N; i++) {
                ansNoCarry[i] = ans[i + 1];
            }
        }
        return ans[0] == 0 ? ansNoCarry : ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8, 9, 8};
        System.out.println(Arrays.toString(plusOne(arr)));
    }
}
