package com.jmc.algorithm.weekProblem.class_2021_11_4_week;

import java.util.Arrays;

/**
 * 限制：0 <= start <= end，0 <= target <= 64
 * [start,end]范围上的数字，有多少数字二进制中1的个数等于target
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/1 12:07
 **/
public class Code03_StartToEndBinaryOneTarget {

    public static long ways1(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }
        long ans = 0;
        for (long i = start; i <= end; i++) {
            if (bits(i) == target) {
//                System.out.print(String.format("%s ", i));
                ans++;
            }
        }
        return ans;
    }

    private static int bits(long num) {
        int bit = 0;
        for (long i = 63; i >= 0; i--) {
            if (((1l << i) & num) != 0) {
                bit++;
            }
        }
        return bit;
    }

    public static long ways2(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }

        int eHigh = 62;
        while (((1l << eHigh) & end) == 0) {
            eHigh--;
        }

        if (start == 0) {
            return process2(eHigh, 0, target, end);
        } else {
            // 4-8范围->0-8范围 - 0-3范围
            start--;
            int sHigh = 62;
            while (((1l << sHigh) & start) == 0) {
                sHigh--;
            }
            return process2(eHigh, 0, target, end) - process2(sHigh, 0, target, start);
        }
    }

    private static long process2(int index, int less, int rest, long num) {
        if (rest > index + 1) {
            return 0;
        }

        if (rest == 0) {
            return 1;
        }

        if (less == 1) {
            // 比原num小了
            if (rest == index + 1) {
                return 1;
            } else {
                return process2(index - 1, 1, rest - 1, num) + process2(index - 1, 1, rest, num);
            }
        } else {
            // 当前位置跟num对应位置相等
            // 11011
            if (rest == index + 1) {
                return ((1l << index) & num) == 0 ? 0 : process2(index - 1, less, rest, num);
            } else {
                return ((1l << index) & num) == 0 ? process2(index - 1, less, rest, num) : process2(index - 1, 1, rest, num) + process2(index - 1, 0, rest - 1, num);
            }
        }
    }

    public static long ways3(long start, long end, int target) {
        if (start < 0 || end < 0 || start > end || target < 0) {
            return -1;
        }

        if (start == end && start == 0) {
            return target == 0 ? 1 : 0;
        }

        int eHigh = 62;
        while (((1l << eHigh) & end) == 0) {
            eHigh--;
        }
        long[][][] dpEnd = new long[64][2][target + 1];
        for (int i = 0; i < 63; i++) {
            Arrays.fill(dpEnd[i][0], -1);
            Arrays.fill(dpEnd[i][1], -1);
        }
        long ans;
        long ansStart;
        long ansEnd = process3(eHigh, 0, target, end, dpEnd);
        if (start == 0) {
            ans = ansEnd;
        } else {
            // 4-8 -> 0-8 减去 0-3
            start--;
            int sHigh = 62;
            while (((1 << sHigh) & start) == 0) {
                sHigh--;
            }
            long[][][] dpStart = new long[64][2][target + 1];
            for (int i = 0; i < 64; i++) {
                Arrays.fill(dpStart[i][0], -1);
                Arrays.fill(dpStart[i][1], -1);
            }
            ansStart = process3(sHigh, 0, target, start, dpStart);
            ans = ansEnd - ansStart;
        }
        return ans;
    }

    private static long process3(int index, int less, int rest, long num, long[][][] dp) {
        if (rest > index + 1) {
            dp[index][less][rest] = 0;
            return  0;
        }

        if (rest == 0) {
            dp[index][less][rest] = 1;
            return  1;
        }

        if (dp[index][less][rest] != -1) {
            return dp[index][less][rest];
        }

        long ans;
        if (less == 1) {
            // 说明之前做的决定已经小于num了
            if (rest == index + 1) {
                ans = 1;
            } else {
                ans = process3(index - 1, 1, rest - 1, num, dp) + process3(index - 1, 1, rest, num, dp);
            }
        } else {
            // 说明之前做的决定等于num目前来到的index位置
            if (rest == index + 1) {
                ans = ((1l << index) & num) == 0 ? 0 : process3(index - 1, less, rest - 1, num, dp);
            } else {
                ans = ((1l << index) & num) == 0 ? process3(index - 1, 0, rest, num, dp) : process3(index - 1, 1, rest, num, dp) + process3(index - 1, 0, rest - 1, num, dp);
            }
        }

        dp[index][less][rest] = ans;
        return ans;
    }

    public static void main(String[] args) {

        long start = 88193819381L;
        long end = 92371283713182371L;
        int target = 30;
        printBinary(end);
        System.out.println(ways3(start, end, target));
        System.out.println(ways2(start, end, target));
        System.out.println(ways1(start, end, target));
    }

    private static int getMostHigh(long num) {
        int mostHigh = 31;
        while (((1 << mostHigh) & num) == 0) {
            mostHigh--;
        }
        return mostHigh;
    }

    private static void printBinary(long num) {
        // 10 -> 00001010
        for (int i = 63; i >= 0; i--) {
            System.out.print((((1l << i) & num) == 0 ? '0' : '1'));
        }
        System.out.println();
    }
}
