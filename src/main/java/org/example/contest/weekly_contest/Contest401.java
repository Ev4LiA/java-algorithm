package org.example.contest.weekly_contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Contest401 {
    // Find the Child Who Has the Ball After K Seconds
    public int numberOfChild(int n, int k) {
        int a = k / (n - 1), remainder = k % (n - 1);
        if (a % 2 == 0) {
            return remainder;
        } else {
            return n - remainder;
        }
    }

    // Find the N-th Value After K Seconds
    /* METHOD 1: BRUTE FORCE */
    public int valueAfterKSeconds(int n, int k) {
        final int MOD = 1000000007;

        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int i = 0; i < k; i++) {
            int sum = 1;
            for (int j = 1; j < n; j++) {
                int temp = (sum + arr[j]) % MOD;
                arr[j] = (sum + arr[j]) % MOD;
                sum = temp % MOD;
            }
        }
        return arr[n - 1];
    }

    // Maximum Total Reward Using Operations I
    public static int[][] dp;

    public int maxTotalReward(int[] rewardValues) {
        int n = rewardValues.length;
        Arrays.sort(rewardValues);
        dp = new int[n + 1][4001];

        for (int[] sub : dp) {
            Arrays.fill(sub, -1);
        }

        return dfs(rewardValues, 0, 0);
    }

    private int dfs(int[] rewardValues, int sum, int index) {
        if (index >= rewardValues.length) return sum;
        if (dp[index][sum] != -1) {
            return dp[index][sum];
        }

        int op1 = dfs(rewardValues, sum, index + 1);
        int op2 = 0;
        if (rewardValues[index] > sum) {
            op2 = dfs(rewardValues, sum + rewardValues[index], index + 1);
        }

        dp[index][sum] =  Math.max(op1, op2);
        return dp[index][sum];
    }
}
