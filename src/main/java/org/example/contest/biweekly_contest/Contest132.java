package org.example.contest.biweekly_contest;

import java.util.Arrays;

public class Contest132 {
    // 3174. Clear Digits
    public String clearDigits(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(chars[i])) {
                chars[i] = '_';
                int j = i;
                while (j >= 0) {
                    if (chars[j] != '_') {
                        chars[j] = '_';
                        break;
                    } else {
                        j--;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c != '_') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 3175. Find The First Player to win K Games in a Row
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        int max = skills[0], res = 0;
        if (k >= n - 1) {
            for (int i = 1; i < n; i++) {
                if (skills[i] > max) {
                    res = i;
                    max = skills[i];
                }
            }
        } else {
            int count = 0;
            for (int i = 1; i < n; i++) {
                if (skills[i] < max) {
                    count++;
                } else {
                    max = skills[i];
                    count = 1;
                    res = i;
                }
                if (count == k) return res;

            }
        }
        return res;
    }

    // 3176. Find the Maximum Length of a Good Subsequence I
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int m = 0; m < i; m++) {
                    if (nums[m] == nums[i]) {
                        dp[i][j] = Math.max(dp[i][j], dp[m][j] + 1);
                    } else if (j > 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + 1);

                    }
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    // 3177. Find the Maximum Length of a Good Subsequence II
//    public int maximumLengthII(int[] nums, int k) {
//        int n = nums.length;
//        int[][] dp = new int[k + 1][n];
//        int[][] max = new int[k + 1][n];
//        HashMap<Integer, Integer> last = new HashMap<>();
//
//        for (int i = 0; i <= k; i++) {
//            for (int j = 0; j < n; j++) {
//
//            }
//        }
//    }
}
