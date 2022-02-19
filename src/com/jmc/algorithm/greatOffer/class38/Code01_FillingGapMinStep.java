package com.jmc.algorithm.greatOffer.class38;

/**
 * 来自字节
 * 给定2个数a和b
 * 第1轮，把1给a或者b
 * 第2轮，把1给a或者b
 * ...
 * 第i轮，把1给a或者b
 * 想让a和b值一样大，至少需要多少轮
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/14 17:55
 **/
public class Code01_FillingGapMinStep {
    public static int minStep(int a, int b) {
        if (a == b) {
            return 0;
        }

        int abs = Math.abs(a - b);
        int begin = best(abs << 1);
        for (; (begin *(begin + 1) / 2 - abs) % 2 != 0;) {
            begin++;
        }

        return begin;
    }

    private static int best(int abs) {
        int L = 0;
        int R = 1;
        int ans = 0;
        for (; R <= abs; R *= 2) {
            if (R * (R + 1) < abs) {
                L = R;
            } else {
                break;
            }

        }

        // 二分
        while (L <= R) {
            int mid = (L + R) / 2;
            if (mid * (mid + 1) >= abs) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 3;
        System.out.println(minStep(a, b));
    }
}
