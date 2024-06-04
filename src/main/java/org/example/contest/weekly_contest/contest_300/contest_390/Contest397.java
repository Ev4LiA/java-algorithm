package org.example.contest.weekly_contest.contest_300.contest_390;

import java.util.Arrays;
import java.util.List;

public class Contest397 {
    // 3146. Permutation Difference between Two Strings
    public int findPermutationDifference(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += Math.abs(i - t.indexOf(s.charAt(i)));
        }
        return res;
    }

    // 3147. Taking Maximum Energy From the Mystic Dungeon
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

    // 3148. Maximum Difference Score in a Grid
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

    // 3149. Find the Minimum Cost Array Permutation
    static final int MISSING = -1;

    int n;
    int[] nums;
    int[][][] cache, take;

    /**
     * Returns the minimum "remaining" score <em>|last - nums[perm[i]]| + |perm[i] - nums[perm[i + 1]] + ... +
     * |perm[n - 1] - nums[first]|</em>, where <em>perm[i], perm[i + 1], ..., perm[n - 1]</em> are not in
     * {@code bitset}.
     * <p>
     * Mutates {@code take}.
     *
     * @param bitset the set of elements taken
     * @param first  the first element taken
     * @param last   the most recent element taken
     * @return the minimum "remaining" score
     */
    int dp(int bitset, int first, int last) {
        if (cache[bitset][first][last] != MISSING) {
            return cache[bitset][first][last];
        }

        if (bitset == (1 << n) - 1) {
            return Math.abs(last - nums[first]);
        }

        int resCost = Integer.MAX_VALUE;
        int resTake = 0;
        for (int i = 0; i < n; ++i) {
            if ((bitset & (1 << i)) == 0) {
                int potential = dp(bitset | (1 << i), first, i) + Math.abs(last - nums[i]);
                if (potential < resCost) {
                    resCost = potential;
                    resTake = i;
                }
            }
        }

        take[bitset][first][last] = resTake;
        return cache[bitset][first][last] = resCost;
    }

    public int[] findPermutation(int[] nums) {
        n = nums.length;
        this.nums = nums;

        cache = new int[1 << n][n][n];
        take = new int[1 << n][n][n];
        for (int[][] matrix : cache) {
            for (int[] row : matrix) {
                Arrays.fill(row, MISSING);
            }
        }

        int resCost = dp(1, 0, 0);
        int resTake = 0;
        for (int i = 1; i < n; ++i) {
            int potential = dp(1 << i, i, i);
            if (potential < resCost) {
                resCost = potential;
                resTake = i;
            }
        }

        int[] perm = new int[n];
        for (int i = 0, bitset = 1 << resTake, first = resTake, last; i < n; ++i) {
            perm[i] = last = resTake;

            bitset |= 1 << (resTake = take[bitset][first][last]);
        }
        return perm;
    }
}
