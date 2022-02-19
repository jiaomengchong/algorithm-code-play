package com.jmc.algorithm.greatOffer.class44;

/**
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * 写一个函数来计算范围在 [low, high] 之间中心对称数的个数。
 * 示例:
 * 输入: low = "50", high = "100"
 * 输出: 3
 * 解释: 69，88 和 96 是三个在该范围内的中心对称数
 * 注意:
 * 由于范围可能很大，所以 low 和 high 都用字符串表示。
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/15 17:03
 **/
public class Problem_0248_StrobogrammaticNumberIII {
    public static int strobogrammaticInRange(String l, String h) {
        char[] low = l.toCharArray();
        char[] high = h.toCharArray();
        if (!checkValid(low, high)) {
            return 0;
        }

        if (low.length == high.length) {
            int up1 = up(low, false, 0, 1);
            int up2 = up(high, false, 0, 1);
            return up2 - up1 + (valid(high) ? 1 : 0);
        }

        int ans = 0;
        for (int i = low.length + 1; i < high.length; i++) {
            ans += all(i, true);
        }
        ans += up(low, false, 0, 1);
        ans += down(high, false, 0, 1);
        return ans;
    }

    private static int down(char[] high, boolean leftLess, int left, int rightLessEqualMore) {
        int N = high.length;
        int right = N - 1 - left;
        if (left > right) {
            return leftLess || rightLessEqualMore != 2 ? 1 : 0;
        }

        if (leftLess) {
            return num(N - (left << 1));
        }

        // left从小于开始尝试
        int ans = 0;
        for (char tryChar = (N != 1 && left == 0) ? '1' : '0'; tryChar < high[left]; tryChar++) {
            if (convert(tryChar, left != right) != -1) {
                ans += down(high, true, left + 1, rightLessEqualMore);
            }
        }

        // left等于讨论可能性
        int convert = convert(high[left], left != right);
        if (convert != -1) {
            if (convert > high[right]) {
                ans += down(high, false, left + 1, 2);
            } else if (convert == high[right]) {
                ans += down(high, false, left + 1, 1);
            } else {
                ans += down(high, false, left + 1, 0);
            }
        }

        return ans;
    }

    private static int all(int i, boolean init) {
        if (i == 0) {
            return init ? 0 : 1;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return init ? 4 : 5;
        }
        return all(2, init) * all(i - 2, false);
    }

    private static boolean valid(char[] high) {
        int N = high.length;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            int convert = convert(high[left], left != right);
            if (convert != high[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static int up(char[] low, boolean leftMore, int left, int rightLessEqualMore) {
        int N = low.length;
        int right = N - 1 - left;
        if (left > right) {
            return leftMore || rightLessEqualMore != 0 ? 1 : 0;
        }

        if (leftMore) {
            return num(N - (left << 1));
        }

        // left从大于尝试
        int ans = 0;
        for (char tryChar = (char) (low[left] + 1); tryChar <= '9'; tryChar++) {
            if (convert(tryChar, left != right) != -1) {
                ans += up(low, true, left + 1, rightLessEqualMore);
            }
        }

        // 相等情况
        int equalConvert = convert(low[left], left != right);
        if (equalConvert != -1) {
            if (equalConvert > low[right]) {
                ans += up(low, false, left + 1, 2);
            } else if (equalConvert == low[right]) {
                ans += up(low, false, left + 1, 1);
            } else {
                ans += up(low, false, left + 1, 0);
            }
        }

        return ans;
    }

    private static int convert(char cha, boolean diff) {
        switch (cha) {
            case '0':
                return '0';
            case '1':
                return '1';
            case '6':
                return diff ? '9' : -1;
            case '9':
                return diff ? '6' : -1;
            case '8':
                return '8';
            default:
                return -1;
        }
    }

    private static int num(int bits) {
        if (bits == 1) {
            return 3;
        }
        if (bits == 2) {
            return 5;
        }
        return 5 * num(bits - 2);
    }

    private static boolean checkValid(char[] low, char[] high) {
        if (low.length != high.length) {
            return low.length < high.length;
        }

        for (int i = 0; i < low.length; i++) {
            if (low[i] != high[i]) {
                return low[i] < high[i];
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String low = "181";
        String high = "100000099991";
        System.out.println(strobogrammaticInRange(low, high));
    }
}
