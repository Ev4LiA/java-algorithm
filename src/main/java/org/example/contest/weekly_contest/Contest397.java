package org.example.contest.weekly_contest;

import java.util.Arrays;
import java.util.List;

public class Contest397 {
    // Permutation Difference between Two Strings
    public int findPermutationDifference(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += Math.abs(i - t.indexOf(s.charAt(i)));
        }
        return res;
    }

    // Taking Maximum Energy From the Mystic Dungeon
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] sum = new int[n];
        int[] arr = new int[k];

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            arr[i % k] += energy[i];
        }

        for (int i = 0; i < k; i++) {
            sum[i] = arr[i];
            max = Math.max(sum[i], max);
        }

        for (int i = k; i < n; i++) {
            sum[i] = sum[i - k] - energy[i - k];
            max = Math.max(sum[i], max);
        }

        return max;
    }

    // Maximum Difference Score in a Grid
    public int maxScore(List<List<Integer>> grid) {
        int rows = grid.size();
        int cols = grid.get(0).size();
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        dp[rows - 1][cols - 1] = grid.get(rows - 1).get(cols - 1);

        int res = Integer.MIN_VALUE;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i < rows - 1) {
                   dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }

                if (j < cols - 1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
                }

                dp[i][j] = Math.max(dp[i][j], grid.get(i).get(j));
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i < rows - 1) {
                    res = Math.max(res, dp[i + 1][j] - grid.get(i).get(j));
                }
                if (j < cols - 1) {
                    res = Math.max(res, dp[i][j + 1] - grid.get(i).get(j));
                }
            }
        }
        return res;
    }
}
