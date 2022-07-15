package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class Problem_1290_ConvertBinaryNumberInALinkedListToInteger {
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

    public static int getDecimalValue(ListNode head) {
        // 101
        int ans = 0;
        ListNode cur = head;
        StringBuffer sb = new StringBuffer();
        while (cur != null) {
            sb.append(cur.val);
            cur = cur.next;
        }

        int N = sb.length();
        for (int i = N - 1; i >= 0; i--) {
            if (sb.charAt(i) == '1') {
                ans += (1 << (N - 1 - i));
            }
        }
        return ans;
    }

    public static int getDecimalValue1(ListNode head) {
        int ans = 0;
        // 101
        // 1*2^2 + 0*2^1 + 1*2^0 = 5
        ListNode cur = head;
        while (cur != null) {
            ans = ans * 2 + cur.val;
            cur = cur.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(1);
        System.out.println(getDecimalValue(head));
        System.out.println(getDecimalValue1(head));
    }
}
