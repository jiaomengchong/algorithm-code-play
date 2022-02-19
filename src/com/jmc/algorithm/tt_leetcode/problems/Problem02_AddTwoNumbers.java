package com.jmc.algorithm.tt_leetcode.problems;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头
 * <p>
 * 来源：力扣（LeetCode）
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/22 17:55
 */
public class Problem02_AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        Queue<Integer> queue1 = new LinkedBlockingQueue<>();
        Queue<Integer> queue2 = new LinkedBlockingQueue<>();
        Queue<Integer> carryBit = new LinkedBlockingQueue<>();
        Queue<Integer> ansQueue = new LinkedBlockingQueue<>();
        while (l1 != null) {
            queue1.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            queue2.add(l2.val);
            l2 = l2.next;
        }

        int abs = Math.abs(queue1.size() - queue2.size());
        if (abs > 0) {
            if (queue1.size() > queue2.size()) {
                for (int i = 0; i < abs; i++) {
                    queue2.add(0);
                }
            } else {
                for (int i = 0; i < abs; i++) {
                    queue1.add(0);
                }
            }
        }

        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            int ans = (!queue1.isEmpty() ? queue1.poll() : 0) + (!queue2.isEmpty() ? queue2.poll() : 0) + (!carryBit.isEmpty() ? carryBit.poll() : 0);
            if (ans >= 10) {
                ans = ans - 10;
                carryBit.add(1);
            }
            ansQueue.add(ans);
        }


        ListNode head = ansQueue.isEmpty() ? null : new ListNode(ansQueue.poll());
        ListNode current = head;
        while (!ansQueue.isEmpty()) {
            current.next = ansQueue.isEmpty() ? null : new ListNode(ansQueue.poll());
            current = current.next;
        }

        if (!carryBit.isEmpty()) {
            current.next = new ListNode(carryBit.poll());
        }

        return head;
    }

    public static void main(String[] args) {
        /*ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);*/

        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(9);
        head1.next.next = new ListNode(9);
        head1.next.next.next = new ListNode(9);
        head1.next.next.next.next = new ListNode(9);
        head1.next.next.next.next.next = new ListNode(9);
        head1.next.next.next.next.next.next = new ListNode(9);

        ListNode head2 = new ListNode(9);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(9);
        head2.next.next.next = new ListNode(9);

        /*ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(9);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);
        head2.next.next.next = new ListNode(9);*/

        ListNode node = addTwoNumbers(head1, head2);
        System.out.println(node.toString());

    }
}
