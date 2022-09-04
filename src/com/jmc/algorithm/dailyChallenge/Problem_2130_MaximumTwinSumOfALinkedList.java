package com.jmc.algorithm.dailyChallenge;


import java.util.ArrayList;
import java.util.List;

public class Problem_2130_MaximumTwinSumOfALinkedList {
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

    public static int pairSum(ListNode head) {
        int ans = Integer.MIN_VALUE;
        ListNode cur = head;
        List<Integer> pairList = new ArrayList<>();
        while (cur != null) {
            pairList.add(cur.val);
            cur = cur.next;
        }
        int size = pairList.size();
        ans = Math.max(ans, pairList.get(0) + pairList.get(size - 1));
        for (int i = 1; i <= size / 2 - 1; i++) {
            ans = Math.max(ans, pairList.get(i) + pairList.get(size - i - 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        //  0  1   2   3   4   5   6   7   8  9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24 25  26  27  28  29  30  31 32  33  34  35  36  37
        // [7, 57, 13, 31, 17, 65, 32, 3, 97, 22, 7, 20, 69, 35, 69, 75, 13, 33, 50, 80, 64, 71, 15, 28, 2, 27, 39, 48, 13, 22, 84, 5, 51, 46, 26, 78, 56, 63]
        // 130
        ListNode head = new ListNode(7);
        head.next = new ListNode(57);
        head.next.next = new ListNode(13);
        head.next.next.next = new ListNode(31);
        head.next.next.next.next = new ListNode(17);
        head.next.next.next.next.next = new ListNode(65);
        head.next.next.next.next.next.next = new ListNode(32);
        head.next.next.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next.next.next = new ListNode(97);
        head.next.next.next.next.next.next.next.next.next = new ListNode(22);
        head.next.next.next.next.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next.next.next.next.next = new ListNode(20);
        head.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(69);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(35);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(69);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(75);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(13);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(33);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(50);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(80);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(64);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(71);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(15);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(28);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(27);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(39);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(48);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(13);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(22);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(84);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(51);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(46);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(26);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(78);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(56);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(63);
        System.out.println(pairSum(head));
    }
}
