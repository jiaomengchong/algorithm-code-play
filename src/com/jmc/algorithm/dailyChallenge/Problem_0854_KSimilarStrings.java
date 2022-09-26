package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/k-similar-strings/
 */
public class Problem_0854_KSimilarStrings {
    int ans = Integer.MAX_VALUE;
    public int kSimilarity(String s1, String s2) {
        return dfs(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    private int dfs(char[] arr1, char[] arr2, int index, int cur) {
        if (cur > ans) {
            return ans;
        }

        if (index == arr1.length - 1) {
            ans = Math.min(ans, cur);
            return ans;
        }

        for (int i = index; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                for (int j = i + 1; j < arr2.length; j++) {
                    if (arr1[i] == arr2[j] && arr1[j] != arr2[j]) {
                        swap(arr2, i, j);
                        dfs(arr1, arr2, index + 1, cur + 1);
                        swap(arr2, i, j);
                        if (arr1[j] == arr2[i]) {
                            break;
                        }
                    }
                }
                return ans;
            }
        }

        ans = Math.min(ans, cur);
        return ans;
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bca";
        Problem_0854_KSimilarStrings kSimilarStrings = new Problem_0854_KSimilarStrings();
        System.out.println(kSimilarStrings.kSimilarity(s1, s2));
    }
}
