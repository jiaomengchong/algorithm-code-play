package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/linked-list-components/
 */
public class Problem_0817_LinkedListComponents {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int numComponents(ListNode head, int[] nums) {
        // [0,3,2,4,1]
        // [3,0,2]
        int N = nums.length;
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < N; i++) {
            sets.add(nums[i]);
        }
        int ans = 0;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            if (sets.contains(cur.val)) {
                ans += pre != null && sets.contains(pre.val) ? 0 : 1;
            }
            pre = cur;
            cur = cur.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        int[] nums = new int[]{3, 0, 2};
        System.out.println(numComponents(head, nums));
    }
}
