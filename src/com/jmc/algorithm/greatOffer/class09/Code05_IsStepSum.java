package com.jmc.algorithm.greatOffer.class09;

/**
 * 定义何为step sum？
 * 比如680，680 + 68 + 6 = 754，680的step sum叫754
 * 给定一个正数num，判断它是不是某个数的step sum
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/25 17:32
 */
public class Code05_IsStepSum {
    public static boolean isStepSum(int num) {
        int L = 0;
        int R = num;
        int mid;
        int cur;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            cur = stepSum(mid);
            if (cur == num) {
                System.out.println(mid);
                return true;
            } else if (cur > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return false;
    }

    private static int stepSum(int num) {
        int ans = 0;
        while (num != 0) {
            ans +=num;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(isStepSum(111));
    }
}
