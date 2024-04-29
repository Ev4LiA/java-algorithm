package org.example.contest.biweekly_contest;

import java.util.*;

public class Contest129 {
    // 3127. Make a Square with the Same Color
    public boolean canMakeSquare(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int black = 0, white = 0;
                for (int r = i; r <= i + 1; r++) {
                    for (int c = j; c <= j + 1; c++) {
                        if (grid[r][c] == 'B') {
                            black++;
                        } else {
                            white++;
                        }
                    }
                }

                if (black >= 3 || white >= 3) return true;
            }
        }
        return false;
    }

    // 3128. Right Triangles
    public long numberOfRightTriangles(int[][] grid) {
        long res = 0;
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[m];
        HashMap<Integer, Set<Integer>> mapCol = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int oneCount = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    mapCol.computeIfAbsent(j, key -> new HashSet<>()).add(i);
                    oneCount++;
                }
            }
            arr[i] = oneCount;
        }

        for (Map.Entry<Integer, Set<Integer>> entry : mapCol.entrySet()) {
            Set<Integer> set = entry.getValue();
            int numberOfOneCol = set.size();
            for (Integer row : set) {
                int numberOfOneRow = arr[row];
                if (numberOfOneRow > 1) {
                    res += (long) (numberOfOneCol - 1) * (numberOfOneRow - 1);
                }
            }
        }

        return res;
    }

    // 3129. Find All Possible Stable Binary Arrays I
    int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        long[][][] dp = new long[zero + 1][one + 1][2];
        dp[0][0][0] = dp[0][0][1] = 1;
        // dp[i][j][num] -> number of sub arrays with 'i' zeros , 'j' ones and ending with num (0 or 1)

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                for (int k = 0; k <= limit; k++) {
                    if (i - k >= 0) {
                        dp[i][j][1] = (dp[i][j][1] + dp[i - k][j][0]) % MOD;
                    }

                    if (j - k >= 0) {
                        dp[i][j][0] = (dp[i][j][0] + dp[i][j - k][1]) % MOD;
                    }
                }
            }
        }
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }

    // 3130. Find All Possible Stable Binary Arrays II
    public int numberOfStableArraysII(int zero, int one, int limit) {
        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= Math.min(one, limit); i++) {
            dp[0][i][1] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] + MOD - dp[i - limit - 1][j][1]) % MOD;
                }

                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] + MOD - dp[i][j - limit - 1][0]) % MOD;
                }
            }
        }
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
}
