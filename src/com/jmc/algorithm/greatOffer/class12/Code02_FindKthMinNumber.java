package com.jmc.algorithm.greatOffer.class12;

/**
 * 测试链接：https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/6 10:07
 */
public class Code02_FindKthMinNumber {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        double ans = 0;
        boolean even = (len & 1) != 0 ;
        if (len1 != 0 && len2 != 0) {
            if (even) {
                ans = (findKthNum1(nums1, nums2, len / 2) + findKthNum1(nums1, nums2, len / 2 + 1)) / 2d;
            } else {
                ans = findKthNum1(nums1, nums2, len / 2 + 1);
            }
        } else if (len1 != 0) {
            if (even) {
                ans = (nums1[(len1 - 1) / 2] + nums1[len1 / 2]) / 2d;
            } else {
                ans = nums1[len1 / 2];
            }
        } else if (len2 != 0) {
            if (even) {
                ans = (nums2[(len2 - 1) / 2] + nums2[len2 / 2]) / 2d;
            } else {
                ans = nums2[len2 / 2];
            }
        }
        return ans;
    }

    /**
     * 获取上中点
     *
     * @param arr1
     * @param s1
     * @param e1
     * @param arr2
     * @param s2
     * @param e2
     * @return
     */
    public static int getUpMedium(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        if (s1 == e1) {
            return Math.min(arr1[s1], arr2[s2]);
        }

        int m1 = (s1 + e1) / 2;
        int m2 = (s2 + e2) / 2;
        if (arr1[m1] == arr2[m2]) {
            return arr1[m1];
        }

        int ans = 0;
        if (((e1 - s1 + 1) & 1) != 0) {
            // 如果s1~e1是奇数个
            if (arr1[m1] > arr2[m2]) {
                // 比较m1 - 1 与 m2的值
                if (arr1[m1 - 1] <= arr2[m2]) {
                    return arr2[m2];
                } else {
                    e1 = m1 - 1;
                    s2 = m2 + 1;
                    ans = getUpMedium(arr1, s1, e1, arr2, s2, e2);
                }
            } else {
                // 比较m1与m2 - 1的值
                if (arr1[m1] >= arr2[m2 - 1]) {
                    return arr1[m1];
                } else {
                    s1 = m1 + 1;
                    e2 = m2 - 1;
                    ans = getUpMedium(arr1, s1, e1, arr2, s2, e2);
                }
            }
        } else {
            // 如果s1~e1是偶数个
            if (arr1[m1] > arr2[m2]) {
                e1 = m1;
                s2 = m2 + 1;
                ans = getUpMedium(arr1, s1, e1, arr2, s2, e2);
            } else {
                s1 = m1 + 1;
                e2 = m2;
                ans = getUpMedium(arr1, s1, e1, arr2, s2, e2);
            }

        }
        return ans;
    }

    /**
     * 2个有序数组不一定相等，找到第k小，时间复杂度可以达到O(log(min(m, n)))
     *
     * @param arr1
     * @param arr2
     * @param kth
     * @return
     */
    public static int findKthNum1(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        // 分3种情况讨论
        // 范围在短数组内
        if (kth <= shorts.length) {
            return getUpMedium(arr1, 0, kth - 1, arr2, 0, kth - 1);
        }
        // 范围：longs < kth <= 整体
        if (longs.length < kth && kth <= (longs.length + shorts.length)) {
            if (longs[kth - shorts.length - 1] >= shorts[shorts.length - 1]) {
                return longs[kth - shorts.length - 1];
            }
            if (shorts[kth - longs.length - 1] >= longs[longs.length - 1]) {
                return shorts[kth - longs.length - 1];
            }
            return getUpMedium(shorts, kth - longs.length, shorts.length - 1, longs, kth - shorts.length, longs.length - 1);
        }
        // 范围：shorts < kth <= longs
        if (longs[kth - shorts.length - 1] >= shorts[shorts.length - 1]) {
            return longs[kth - shorts.length - 1];
        }
        return getUpMedium(shorts, 0, shorts.length - 1, longs, kth - shorts.length, kth - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{6, 7, 8, 9, 10};
        System.out.println(getUpMedium(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1));

        arr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(findKthNum1(arr1, arr2, 15));

        arr1 = new int[]{1, 3};
        arr2 = new int[]{2};
        System.out.println(findMedianSortedArrays(arr1, arr2));

        arr1 = new int[]{1, 2};
        arr2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(arr1, arr2));

        arr1 = new int[]{};
        arr2 = new int[]{1};
        System.out.println(findMedianSortedArrays(arr1, arr2));

        arr1 = new int[]{2};
        arr2 = new int[]{};
        System.out.println(findMedianSortedArrays(arr1, arr2));

        arr1 = new int[]{3, 4};
        arr2 = new int[]{};
        System.out.println(findMedianSortedArrays(arr1, arr2));

        arr1 = new int[]{};
        arr2 = new int[]{1, 2};
        System.out.println(findMedianSortedArrays(arr1, arr2));

        arr1 = new int[]{};
        arr2 = new int[]{1, 2, 3, 4, 5};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }
}
