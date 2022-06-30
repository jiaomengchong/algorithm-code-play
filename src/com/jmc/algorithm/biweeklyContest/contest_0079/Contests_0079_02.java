package com.jmc.algorithm.biweeklyContest.contest_0079;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/contest/biweekly-contest-79/problems/sender-with-largest-word-count/
 */
public class Contests_0079_02 {
    public static String largestWordCount(String[] messages, String[] senders) {
        // 输入：
        // messages = ["Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"]
        // senders = ["Alice","userTwo","userThree","Alice"]
        // 输出："Alice"
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < senders.length; i++) {
            String[] wordsCount = messages[i].split(" ");
            map.put(senders[i], map.getOrDefault(senders[i], 0) + wordsCount.length);
        }

        List<Map.Entry<String, Integer>> lists = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            lists.add(entry);
        }

        Collections.sort(lists, new MyComparator());

        return lists.get(0).getKey();
    }

    public static class MyComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue() != o1.getValue() ? o2.getValue() - o1.getValue() : o2.getKey().compareTo(o1.getKey());
        }
    }

    public static void main(String[] args) {
        String[] messages = new String[]{"How is leetcode for everyone", "Leetcode is useful for practice"};
        String[] senders = new String[]{"Bob", "Charlie"};
        System.out.println(largestWordCount(messages, senders));
    }
}
