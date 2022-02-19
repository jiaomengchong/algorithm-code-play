package com.jmc.algorithm.jjb.class28;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/2/18 21:18
 */
public class Code02_AddShortestEnd {
    public static String addShortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] str = getManacherString(s);
        int R = -1;
        int C = -1;
        int[] pArray = new int[str.length];
        int end = -1;
        for (int i = 0; i < str.length; i++) {
            pArray[i] = R > i ? Math.min(pArray[2 * C - i], R - i) : 1;
            while (i + pArray[i] < str.length && i - pArray[i] > -1) {
                if (str[i + pArray[i]] == str[i - pArray[i]]) {
                    pArray[i]++;
                } else {
                    break;
                }
            }
            if (i + pArray[i] > R) {
                R = i + pArray[i];
                C = i;
            }
            if (R == str.length) {
                end = pArray[i];
                break;
            }
        }

        char[] ansChars = new char[s.length() - end + 1];
        for (int i = 0; i < ansChars.length; i++) {
            ansChars[ansChars.length - 1 - i] = str[2 * i + 1];
        }
        return String.valueOf(ansChars);
    }

    public static char[] getManacherString(String s) {
        char[] chars = s.toCharArray();
        char[] manacherChars = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < manacherChars.length; i++) {
            manacherChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return manacherChars;
    }

    public static void main(String[] args) {
        //                           C
        // # a # b # c # d # 1 #  2  #  2  #  1  #
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // a b c d 1 2 2 1
        // 0 1 2 3 4 5 6 7

        //                        c
        // # a # b # c # d # 1 #  2  #  1  #
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
        // a b c d 1 2 1
        // 0 1 2 3 4 5 6
        String str = "xssys1sx343";
        System.out.println(addShortestEnd(str));

    }
}
