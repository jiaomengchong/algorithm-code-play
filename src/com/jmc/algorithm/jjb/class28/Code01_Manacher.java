package com.jmc.algorithm.jjb.class28;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/2/18 16:34
 */
public class Code01_Manacher {
    public static int manacher(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] manacherChars = getManacherString(str);
        int N = manacherChars.length;
        int[] pArray = new int[N];
        int R = -1;
        int C = -1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            pArray[i] = i < R ? Math.min(pArray[2 * C - i], R - i) : 1;
            while (i + pArray[i] < N && i - pArray[i] > -1) {
                if (manacherChars[i + pArray[i]] == manacherChars[i - pArray[i]]) {
                    pArray[i]++;
                } else {
                    break;
                }
            }
            if (i + pArray[i] > R) {
                R = i + pArray[i];
                C = i;
            }
            ans = Math.max(ans, pArray[i]);
        }

        return ans - 1;
    }

    public static char[] getManacherString(String str) {
        char[] chars = str.toCharArray();
        char[] manacherChars = new char[2 * str.length() + 1];
        int index = 0;
        for (int i = 0; i < manacherChars.length; i++) {
            manacherChars[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return manacherChars;
    }

    public static void main(String[] args) {
        String str = "121";
        System.out.println(manacher(str));
    }
}
