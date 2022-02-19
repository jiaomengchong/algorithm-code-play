package com.jmc.algorithm.greatOffer.class30;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/12 12:09
 */
public class Problem_0088_MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int index = nums1.length;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[--index] = nums1[m--];
            } else {
                nums1[--index] = nums2[n--];
            }
        }

        while (m >= 0) {
            nums1[--index] = nums1[m--];
        }

        while (n >= 0) {
            nums1[--index] = nums2[n--];
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{0};
        int m = 0;
        int[] nums2 = new int[]{1};
        int n = 1;
        merge(nums1, m, nums2, n);
    }
}
