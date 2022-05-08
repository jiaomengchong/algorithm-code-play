package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * @Author jmc
 * @Description
 * @Date 2022/5/6 13:44
 **/
public class Problem_0019_RemoveNthNodeFromEndOfList {

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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 1->2->3->4->5->6   n=4
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        System.out.println(String.format("size:%s", size));

        if (size == n && size == 1) {
            return null;
        }

        if (size == n) {
            head = head.next;
            return head;
        }

        int cycle = size - n - 1;
        cur = head;
        while (cycle != 0) {
            cycle--;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }

    // 快慢指针解法
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        // 1->2->3->4->5->6   n=4
        ListNode virtual  = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = virtual;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        ListNode ans = virtual.next;
        return ans;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        removeNthFromEnd1(head, 6);
    }
}
