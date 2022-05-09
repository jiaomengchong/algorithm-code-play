package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/remove-element/
 */
public class Problem_0027_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1 && nums[0] == val) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (right > left && nums[right] == val) {
                right--;
            }

            if (right <= 0) {
                return right < 0 ? 0 : (nums[right] == val ? 0 : 1);
            }

            if (left == right) {
                return nums[left] != val ? left + 1 : left;
            }

            if (nums[left] != val) {
                left++;
            } else {
                // 跟右指针交换
                swap(nums, left, right);
                left++;
            }

        }
        return nums[left] != val ? left + 1 : left;
    }

    // 优化精简代码
    public static int removeElement1(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 输入：nums = [3,2,2,3], val = 3
        // 输出：2, nums = [2,2]
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[--right];
            } else {
                left++;
            }
        }
        return left;
    }

    private static void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(removeElement(nums, val));
        System.out.println(removeElement1(nums, val));
    }
}
