package com.jmc.algorithm.greatOffer.class23;

import java.util.HashSet;

/**
 * 可重合数组：一个数组arr，排序后，除了最左侧数，都有arr[i]=arr[i-1]+1，
 * 如：[5,1,2,4,3]，[6,2,3,1,5,4]都是可重合数组，返回数组arr中最长可整合子数组的长度。
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/20 10:23
 */
public class Code03_LongestIntergratedLength {
    public static int getLILWays1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        HashSet<Integer> sets = new HashSet<>();
        int ans = 1;
        int max;
        int min;
        for (int L = 0; L < N; L++) {
            sets.clear();
            sets.add(arr[L]);
            max = arr[L];
            min = arr[L];
            for (int R = L + 1; R < N; R++) {
                if (sets.contains(arr[R])) {
                    break;
                }
                sets.add(arr[R]);
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                // max - min = 个数 - 1
                if (max - min == sets.size() - 1) {
                    ans = Math.max(ans, sets.size());
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 5, 3, 2, 6, 4, 3 };
        System.out.println(getLILWays1(arr));
    }
}
