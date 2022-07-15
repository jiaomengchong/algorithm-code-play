package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/15 16:14
 **/
public class Problem_1374_GenerateAStringWithCharactersThatHaveOddCounts {
    public static String generateTheString(int n) {
        StringBuffer sb = new StringBuffer();
        if ((n & 1) != 0) {
            while (n > 0) {
                sb.append('a');
                n--;
            }
        } else {
            n--;
            while (n > 0) {
                sb.append('a');
                n--;
            }
            sb.append('b');
        }
        return sb.toString();
    }
}
