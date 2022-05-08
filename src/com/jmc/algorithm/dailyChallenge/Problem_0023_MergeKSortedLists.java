package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 测试链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class Problem_0023_MergeKSortedLists {

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

    // TODO 优化
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 输入：lists = [[1,4,5],[1,3,4],[2,6]]
        // 输出：[1,1,2,3,4,4,5,6]
        List<Integer> retList = new ArrayList<>();
        for (ListNode node : lists) {
            ListNode cur = node;
            while (cur != null) {
                retList.add(cur.val);
                cur = cur.next;
            }
        }

        retList = retList.stream().sorted().collect(Collectors.toList());
        if (retList.size() == 0) {
            return null;
        }
        ListNode ans = new ListNode(retList.get(0));
        ListNode cur = ans;
        for (int i = 1; i < retList.size(); i++) {
            cur.next = new ListNode(retList.get(i));
            cur = cur.next;
        }
        return ans;
    }

    public static class Info {
        private int value;
        private ListNode node;

        public Info(int value) {
            this.value = value;
            node = new ListNode(value);
        }
    }

    public static class MyComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.value - o2.value;
        }

    }

    public static ListNode mergeKLists1(ListNode[] lists) {
        // 输入：lists = [[1,4,5],[1,3,4],[2,6]]
        // 输出：[1,1,2,3,4,4,5,6]
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<Info> pq = new PriorityQueue(new MyComparator());
        for (ListNode node : lists) {
            ListNode cur = node;
            while (cur != null) {
                pq.add(new Info(cur.val));
                cur = cur.next;
            }
        }

        if (pq.size() == 0) {
            return null;
        }

        ListNode dummy = pq.poll().node;
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            cur.next = pq.poll().node;
            cur = cur.next;
        }
        return dummy;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);
        lists[0] = node1;

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);
        lists[1] = node2;

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);
        lists[2] = node3;

        ListNode ret1 = mergeKLists(lists);
        ListNode ret2 = mergeKLists1(lists);
        System.out.println("end!");
    }
}
