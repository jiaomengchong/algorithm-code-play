package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/duplicate-zeros/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/17 22:20
 **/
public class Problem_1089_DuplicateZeros {
    // 双指针
    public static void duplicateZeros(int[] arr) {
        int N = arr.length;
        int left = 0;
        int right = 0;
        while (right < N) {
            if (arr[left] == 0) {
                right += 2;
            } else {
                right++;
            }
            left++;
        }

        left--;

        if (right == N + 1) {
            right = N - 1;
            arr[right--] = 0;
            left--;
        } else {
            right--;
        }

        while (left >= 0 && right >= 0) {
            arr[right--] = arr[left];
            if (arr[left] == 0) {
                arr[right--] = arr[left];
            }
            left--;
        }
    }
}
