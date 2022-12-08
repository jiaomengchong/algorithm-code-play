package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/number-of-different-integers-in-a-string
 */
public class Problem_1805_NumberOfDifferentIntegersInAString {
    public static int numDifferentIntegers(String word) {
        boolean pre = false;
        List<StringBuffer> list = new ArrayList<>();
        for (char ch : word.toCharArray()) {
            if (Character.isDigit(ch)) {
                if (pre) {
                    StringBuffer buffer = list.get(list.size() - 1);
                    if (!buffer.toString().equals("")) {
                        buffer.append(ch - '0');
                    } else {
                        if (ch - '0' != 0) {
                            buffer.append(ch - '0');
                        }
                    }
                } else {
                    StringBuffer sb = new StringBuffer();
                    if (ch - '0' != 0) {
                        sb.append(ch - '0');
                    }
                    list.add(sb);
                }
                pre = true;
            } else {
                pre = false;
            }
        }

        Set<String> sets = new HashSet<>();
        for (StringBuffer sb : list) {
            String value = sb.toString();
            if (!sets.contains(value)) {
                sets.add(value);
            }
        }
        return sets.size();
    }

    public static void main(String[] args) {
        String word = "a1b01c001";
        System.out.println(numDifferentIntegers(word));
    }
}
