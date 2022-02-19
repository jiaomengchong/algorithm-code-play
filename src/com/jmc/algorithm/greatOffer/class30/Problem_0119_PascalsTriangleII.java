package com.jmc.algorithm.greatOffer.class30;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角形II
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/20 17:04
 */
public class Problem_0119_PascalsTriangleII {
    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return null;
        }

        // 1
        // 1 1
        // 1 2 1
        // 1 3 3 1
        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j > 1; j--) {
                ans.set(j - 1, ans.get(j - 1) + ans.get(j - 2));
            }
            ans.add(1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getRow(1));
    }
}
