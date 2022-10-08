package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/advantage-shuffle/
 */
public class Problem_0870_AdvantageShuffle {
    public static int[] advantageCount(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] ans = new int[N];
        List<Integer> nums1List = new ArrayList<>(nums1.length);
        Arrays.sort(nums1);
        for (int i = 0; i < N; i++) {
            nums1List.add(nums1[i]);
        }
        int index = 0;
        for (int i = 0; i < N; i++) {
            int bsIndex = binarySearch(nums1List, 0, nums1List.size() - 1, nums2[i]);
            if (bsIndex == -1) {
                ans[index] = nums1List.get(0);
                nums1List.remove(0);
            } else {
                ans[index] = nums1List.get(bsIndex);
                nums1List.remove(bsIndex);
            }
            index++;
        }
        return ans;
    }

    public static int binarySearch(List<Integer> list, int left, int right, int target) {
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
        // 输出：[24,32,8,12]

        // 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
        // 输出：[2,11,7,15]

        // 输入：[2,0,4,1,2] [1,3,0,0,2]
        // 输出：[2,0,2,1,4]
        int[] nums1 = new int[]{2, 0, 4, 1, 2};
        int[] nums2 = new int[]{1, 3, 0, 0, 2};
        System.out.println(Arrays.toString(advantageCount(nums1, nums2)));
    }
}
