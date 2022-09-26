package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/masking-personal-information/
 */
public class Problem_0831_MaskingPersonalInformation {
    public static String maskPII(String s) {
        if (s.contains("@")) {
            String[] mail = s.split("@");
            char c1 = mail[0].toLowerCase().charAt(0);
            char c2 = mail[0].toLowerCase().charAt(mail[0].length() - 1);
            return String.format("%s*****%s@%s", c1, c2, mail[1].toLowerCase());
        } else {
            char[] chars = s.toCharArray();
            List<Character> list = new ArrayList<>();
            for (char ch : chars) {
                if (Character.isDigit(ch)) {
                    list.add(ch);
                }
            }

            StringBuffer sb = new StringBuffer();
            if (list.size() > 10) {
                sb.append("+");
                for (int i = 0; i < list.size() - 10; i++) {
                    sb.append("*");
                }
                sb.append("-");
            }
            sb.append("***-***-");
            for (int i = list.size() - 4; i < list.size(); i++) {
                sb.append(list.get(i));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        // 输入：s = "1(234)567-890"
        // 输出："***-***-7890"

        // 输入：s = "86-(10)12345678"
        // 输出："+**-***-***-5678"
        String s = "86-(10)12345678";
        System.out.println(maskPII(s));
    }
}
