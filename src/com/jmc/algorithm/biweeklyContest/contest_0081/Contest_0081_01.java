package com.jmc.algorithm.biweeklyContest.contest_0081;

/**
 * 测试链接：https://leetcode.cn/contest/biweekly-contest-81/problems/count-asterisks/
 *
 * @Author jmc
 * @Description
 * @Date 2022/6/29 16:48
 **/
public class Contest_0081_01 {
    public static int countAsterisks(String s) {
        // 输入：s = "yo|uar|e**|b|e***au|tifu|l"
        // 输出：2
        String[] str = s.split("\\|");
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < str[i].length(); j++) {
                    if (str[i].charAt(j) == '*') {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
