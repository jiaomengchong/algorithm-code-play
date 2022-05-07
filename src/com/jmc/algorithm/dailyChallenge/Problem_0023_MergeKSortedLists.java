package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;
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

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[1];
        lists[0] = null;

        /*ListNode node2 = new ListNode(1);
        lists[1] = node2;*/

        mergeKLists(lists);
    }
}
