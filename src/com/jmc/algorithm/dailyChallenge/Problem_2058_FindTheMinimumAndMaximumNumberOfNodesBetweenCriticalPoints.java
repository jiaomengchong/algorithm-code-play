package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/
 */
public class Problem_2058_FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
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

    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] ans = new int[]{-1, -1};
        List<Integer> critical = new ArrayList<>();
        ListNode cur = head;
        ListNode pre = null;
        int index = 1;
        while (cur != null) {
            if (pre != null && cur.next != null) {
                boolean isMore = pre.val > cur.val && cur.val < cur.next.val;
                boolean isLess = pre.val < cur.val && cur.val > cur.next.val;
                if (isMore || isLess) {
                    critical.add(index);
                }
            }
            pre = cur;
            cur = cur.next;
            index++;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        if (critical.size() >= 2) {
            int first = critical.get(0);
            max = critical.get(critical.size() - 1) - first;
            for (int i = 1; i < critical.size(); i++) {
                min = Math.min(min, critical.get(i) - first);
                first = critical.get(i);
            }
            ans[0] = min;
            ans[1] = max;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(2);
        System.out.println(nodesBetweenCriticalPoints(head));
    }
}
