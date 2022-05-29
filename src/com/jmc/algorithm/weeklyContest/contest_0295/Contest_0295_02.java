package com.jmc.algorithm.weeklyContest.contest_0295;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/contest/weekly-contest-295/problems/apply-discount-to-prices/
 */
public class Contest_0295_02 {
    public static String discountPrices(String sentence, int discount) {
        // 输入：sentence = "there are $1 $2 and 5$ candies in the shop", discount = 50
        // 输出："there are $0.50 $1.00 and 5$ candies in the shop"
        String[] words = sentence.split(" ");
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith("$") && check(words[i])) {
                map.put(i, "$" + discount(words[i].substring(1), discount));
            }
        }

        for (Integer key : map.keySet()) {
            words[key] = map.get(key);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (i != words.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static String discount(String price, int discount) {
        if (discount == 100) {
            return "0.00";
        }
        double ret = Double.parseDouble(price) * ((100d - discount) / 100);
        return String.format("%.2f", ret);
    }

    private static boolean check(String word) {
        if (word.length() == 1) {
            return false;
        }
        String sub = word.substring(1);
        if (sub.contains("$")) {
            return false;
        }
        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) - 'a' >= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // "$7383692 5q $5870426"
        // 64
        // "$2658129.25 5q $2113353.50"
        String sentence = "$7383692 5q $5870426";
        int discount = 64;
        // 输出："1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$"
        System.out.println(discountPrices(sentence, discount));
    }
}
