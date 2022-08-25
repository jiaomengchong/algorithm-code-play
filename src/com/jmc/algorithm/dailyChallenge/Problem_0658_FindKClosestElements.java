package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/find-k-closest-elements/
 */
public class Problem_0658_FindKClosestElements {
    // 优化
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int N = arr.length;
        int index = binarySearch(arr, x);
        index = index == -1 ? 0 : index;
        if (arr[index] != x && index < N - 1 && Math.abs(arr[index] - x) > Math.abs(arr[index + 1] - x)) {
            index++;
        }
        int left = index - 1, right = index + 1;
        while (right - left <= k) {
            if (left >= 0 && right < N) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    left--;
                } else {
                    right++;
                }
            } else if (left < 0) {
                right++;
            } else {
                left--;
            }
        }

        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int N = arr.length;
        int index = binarySearch(arr, x);
        index = index == -1 ? 0 : index;
        if (arr[index] != x && index < N - 1 && Math.abs(arr[index] - x) > Math.abs(arr[index + 1] - x)) {
            index++;
        }
        ans.add(arr[index]);
        int left = index - 1, right = index + 1;
        for (int i = 0; i < k - 1; i++) {
            if (left < 0) {
                ans.add(arr[right++]);
                continue;
            }
            if (right >= N) {
                ans.add(arr[left--]);
                continue;
            }
            int absLeft = Math.abs(arr[left] - x);
            int absRight = Math.abs(arr[right] - x);
            if (absLeft <= absRight) {
                ans.add(arr[left--]);
            } else {
                ans.add(arr[right++]);
            }
        }

        Collections.sort(ans);
        return ans;
    }

    public static int binarySearch(int[] arr, int x) {
        int ret = -1;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] <= x) {
                ret = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        // [1,2,3,4,4,4,4,5,5]
        // 3
        // 3
        int[] arr = new int[]{1,2,3,4,4,4,4,5,5};
        int k = 3, x = 3;
        System.out.println(findClosestElements2(arr, k, x));
    }
}
