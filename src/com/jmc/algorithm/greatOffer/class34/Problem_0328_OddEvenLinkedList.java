package com.jmc.algorithm.greatOffer.class34;

import java.util.UUID;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/8/29 23:22
 */
public class Problem_0328_OddEvenLinkedList {
    public class ListNode {
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


    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode firstOdd = null;
        ListNode firstEven = null;
        ListNode odd = null;
        ListNode even = null;
        ListNode next = null;
        int count = 1;

        while (head != null) {
            next = head.next;
            head.next = null;
            if ((count & 1) == 1) {
                firstOdd = firstOdd == null ? head : firstOdd;
                if (odd != null) {
                    odd.next = head;
                }
                odd = head;
            } else {
                firstEven = firstEven == null ? head : firstEven;
                if (even != null) {
                    even.next = head;
                }
                even = head;
            }
            count++;
            head = next;
        }

        if (odd != null) {
            odd.next = firstEven;
        }

        return firstOdd;
    }

    public static void main(String[] args) {
        double random = Math.random();
        String code = (double)((Math.random()*9 + 1)*10000000) + "";
        System.out.println(UUID.randomUUID());
    }
}
