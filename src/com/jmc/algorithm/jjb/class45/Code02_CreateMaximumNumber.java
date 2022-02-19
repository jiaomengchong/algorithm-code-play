package com.jmc.algorithm.jjb.class45;

import java.util.Arrays;

/**
 * 给两个长度分别为M和N的整型数组nums1和nums2，其中每个值都不大于9，再给定一个正数K。
 * 你可以在nums1和nums2中挑选数字，要求一共挑选K个，并且要从左到右挑。返回所有可能的结果中，代表最大数字的结果。
 * 测试链接：https://leetcode.com/problems/create-maximum-number/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/6 10:56
 */
public class Code02_CreateMaximumNumber {
    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (k < 0 || k > len1 + len2) {
            return null;
        }
        int[] res = new int[k];
        int[][] dp1 = getDp(nums1);
        int[][] dp2 = getDp(nums2);
        for (int get1 = Math.max(0, k - len2); get1 <= Math.min(len1, k); get1++) {
            int[] pick1 = maxPick(nums1, dp1, get1);
            int[] pick2 = maxPick(nums2, dp2, k - get1);
            int[] merge = merge(pick1, pick2);
            res = preMoreThanLast(res, 0, merge, 0) ? res : merge;
        }

        return res;
    }

    private static int[] merge(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        int[] res = new int[size];
        for (int i = 0, j = 0, r = 0; r < size; r++) {
            res[r] = preMoreThanLast(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return res;
    }

    private static boolean preMoreThanLast(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    private static int[] maxPick(int[] nums, int[][] dp, int pick) {
        int[] res = new int[pick];
        for (int resIndex = 0, dpRow = 0; pick > 0; resIndex++, pick--) {
            res[resIndex] = nums[dp[dpRow][pick]];
            dpRow = dp[dpRow][pick] + 1;
        }
        return res;
    }

    private static int[][] getDp(int[] nums) {
        int N = nums.length;
        int[][] res = new int[N][N + 1];
        for (int pick = 1; pick <= N; pick++) {
            int maxIndex = N - pick;
            for (int i = N - pick; i >= 0; i--) {
                if (nums[i] >= nums[maxIndex]) {
                    maxIndex = i;
                }
                res[i][pick] = maxIndex;
            }
        }
        return res;
    }

    public static int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (k < 0 || len1 + len2 < k) {
            return null;
        }
        int[][] dp1 = getDp(nums1);
        int[][] dp2 = getDp(nums2);
        int[] ans = new int[k];
        for (int get1 = Math.max(0, k - len2); get1 <= Math.min(len1, k); get1++) {
            int[] pick1 = maxPick(nums1, dp1, get1);
            int[] pick2 = maxPick(nums2, dp2, k - get1);
            int[] merge = mergeBySuffixArray(pick1, pick2);
            ans = moreThan(ans, merge) ? ans : merge;
        }
        return ans;
    }

    private static boolean moreThan(int[] pre, int[] last) {
        int i = 0, j = 0;
        while (i < pre.length && j < last.length && pre[i] == last[j]) {
            i++;
            j++;
        }
        return j == last.length || (i < pre.length && pre[i] > last[j]);
    }

    private static int[] mergeBySuffixArray(int[] arr1, int[] arr2) {
        int size1 = arr1.length;
        int size2 = arr2.length;
        int[] all = new int[size1 + 1 + size2];
        int[] ans = new int[size1 + size2];
        for (int i = 0; i < size1; i++) {
            all[i] = arr1[i] + 2;
        }
        all[size1 + 1] = 1;
        for (int i = 0; i < size2; i++) {
            all[size1 + 1 + i] = arr2[i] + 2;
        }
        DC3 dc3 = new DC3(all, 11);
        int[] rank = dc3.rank;
        int i = 0, j = 0, r = 0;
        while (i < size1 && j < size2) {
            ans[r++] = rank[i] > rank[j + size1 + 1] ? arr1[i++] : arr2[j++];
        }
        while (i < size1) {
            ans[r++] = arr1[i++];
        }
        while (j < size2) {
            ans[r++] = arr2[j++];
        }
        return ans;
    }

    public static class DC3 {

        public int[] sa;

        public int[] rank;

        public int[] height;

        // 构造方法的约定:
        // 数组叫nums，如果你是字符串，请转成整型数组nums
        // 数组中，最小值>=1
        // 如果不满足，处理成满足的，也不会影响使用
        // max, nums里面最大值是多少
        public DC3(int[] nums, int max) {
            sa = sa(nums, max);
            rank = rank();
            height = height(nums);
        }

        private int[] sa(int[] nums, int max) {
            int n = nums.length;
            int[] arr = new int[n + 3];
            for (int i = 0; i < n; i++) {
                arr[i] = nums[i];
            }
            return skew(arr, n, max);
        }

        private int[] skew(int[] nums, int n, int K) {
            int n0 = (n + 2) / 3, n1 = (n + 1) / 3, n2 = n / 3, n02 = n0 + n2;
            int[] s12 = new int[n02 + 3], sa12 = new int[n02 + 3];
            for (int i = 0, j = 0; i < n + (n0 - n1); ++i) {
                if (0 != i % 3) {
                    s12[j++] = i;
                }
            }
            radixPass(nums, s12, sa12, 2, n02, K);
            radixPass(nums, sa12, s12, 1, n02, K);
            radixPass(nums, s12, sa12, 0, n02, K);
            int name = 0, c0 = -1, c1 = -1, c2 = -1;
            for (int i = 0; i < n02; ++i) {
                if (c0 != nums[sa12[i]] || c1 != nums[sa12[i] + 1] || c2 != nums[sa12[i] + 2]) {
                    name++;
                    c0 = nums[sa12[i]];
                    c1 = nums[sa12[i] + 1];
                    c2 = nums[sa12[i] + 2];
                }
                if (1 == sa12[i] % 3) {
                    s12[sa12[i] / 3] = name;
                } else {
                    s12[sa12[i] / 3 + n0] = name;
                }
            }
            if (name < n02) {
                sa12 = skew(s12, n02, name);
                for (int i = 0; i < n02; i++) {
                    s12[sa12[i]] = i + 1;
                }
            } else {
                for (int i = 0; i < n02; i++) {
                    sa12[s12[i] - 1] = i;
                }
            }
            int[] s0 = new int[n0], sa0 = new int[n0];
            for (int i = 0, j = 0; i < n02; i++) {
                if (sa12[i] < n0) {
                    s0[j++] = 3 * sa12[i];
                }
            }
            radixPass(nums, s0, sa0, 0, n0, K);
            int[] sa = new int[n];
            for (int p = 0, t = n0 - n1, k = 0; k < n; k++) {
                int i = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
                int j = sa0[p];
                if (sa12[t] < n0 ? leq(nums[i], s12[sa12[t] + n0], nums[j], s12[j / 3])
                        : leq(nums[i], nums[i + 1], s12[sa12[t] - n0 + 1], nums[j], nums[j + 1], s12[j / 3 + n0])) {
                    sa[k] = i;
                    t++;
                    if (t == n02) {
                        for (k++; p < n0; p++, k++) {
                            sa[k] = sa0[p];
                        }
                    }
                } else {
                    sa[k] = j;
                    p++;
                    if (p == n0) {
                        for (k++; t < n02; t++, k++) {
                            sa[k] = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
                        }
                    }
                }
            }
            return sa;
        }

        private void radixPass(int[] nums, int[] input, int[] output, int offset, int n, int k) {
            int[] cnt = new int[k + 1];
            for (int i = 0; i < n; ++i) {
                cnt[nums[input[i] + offset]]++;
            }
            for (int i = 0, sum = 0; i < cnt.length; ++i) {
                int t = cnt[i];
                cnt[i] = sum;
                sum += t;
            }
            for (int i = 0; i < n; ++i) {
                output[cnt[nums[input[i] + offset]]++] = input[i];
            }
        }

        private boolean leq(int a1, int a2, int b1, int b2) {
            return a1 < b1 || (a1 == b1 && a2 <= b2);
        }

        private boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
            return a1 < b1 || (a1 == b1 && leq(a2, a3, b2, b3));
        }

        private int[] rank() {
            int n = sa.length;
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                ans[sa[i]] = i;
            }
            return ans;
        }

        private int[] height(int[] s) {
            int n = s.length;
            int[] ans = new int[n];
            for (int i = 0, k = 0; i < n; ++i) {
                if (rank[i] != 0) {
                    if (k > 0) {
                        --k;
                    }
                    int j = sa[rank[i] - 1];
                    while (i + k < n && j + k < n && s[i + k] == s[j + k]) {
                        ++k;
                    }
                    ans[rank[i]] = k;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{6, 7};
        int[] nums2 = new int[]{6, 0, 4};
        int k = 5;
        System.out.println(Arrays.toString(maxNumber1(nums1, nums2, k)));
        System.out.println(Arrays.toString(maxNumber2(nums1, nums2, k)));
    }
}
