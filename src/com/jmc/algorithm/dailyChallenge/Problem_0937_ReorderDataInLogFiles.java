package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode-cn.com/problems/reorder-data-in-log-files/
 */
public class Problem_0937_ReorderDataInLogFiles {
    public static class Info {
        private String name;
        private String content;

        public Info(String name, String content) {
            this.name = name;
            this.content = content;
        }
    }

    public static class MyComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.content.equals(o2.content) ? o1.name.compareTo(o2.name) : o1.content.compareTo(o2.content);
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return new String[]{};
        }

        String[] ans = new String[logs.length];
        // 分类
        TreeSet<Info> letterSet = new TreeSet<>(new MyComparator());
        List<String> numList = new ArrayList<>();
        int index = 0;
        for (String log : logs) {
            String[] ls = log.split(" ");
            if (isNum(ls[1].charAt(0))) {
                numList.add(log);
            } else {
                String content = log.substring(ls[0].length() + 1);
                letterSet.add(new Info(ls[0], content));
            }
        }

        for (Info info : letterSet) {
            String name = info.name;
            String content = info.content;
            ans[index++] = String.format("%s %s", name, content);
        }

        for (String num : numList) {
            ans[index++] = num;
        }

        return ans;
    }

    private static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static void main(String[] args) {
        // ["dig1 8 1 5 1","let1 art zero can","dig2 3 6","let2 own kit dig","let3 art zero"]
        // String[] logs = new String[]{"dig1 8 1 5 1", "let1 art zero can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        String[] logs = new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};

        System.out.println(Arrays.toString(reorderLogFiles(logs)));
    }
}
