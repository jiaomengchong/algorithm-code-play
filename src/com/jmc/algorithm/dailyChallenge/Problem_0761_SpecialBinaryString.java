package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/special-binary-string/
 */
public class Problem_0761_SpecialBinaryString {
    public static String makeLargestSpecial(String s) {
        int N = s.length();
        if (s.length() <= 2) {
            return s;
        }

        int cnt = 0, left = 0;
        List<String> specialList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '1') {
                cnt++;
            } else {
                cnt--;
                if (cnt == 0) {
                    String substring = makeLargestSpecial(s.substring(left + 1, i));
                    left = i + 1;
                    specialList.add(String.format("1%s0", substring));
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        specialList.sort((a, b) -> b.compareTo(a));
        for (String str : specialList) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "11011000";
        System.out.println(makeLargestSpecial(s));
    }
}
