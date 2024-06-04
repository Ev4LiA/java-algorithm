package org.example.contest.weekly_contest.contest_300.contest_390;

import java.util.*;

public class Contest398 {
    // 3151. Special Array I
    public boolean isArraySpecial(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] % 2 == nums[i + 1] % 2) {
                return false;
            }
        }
        return true;
    }

    // 3152. Special Array II
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        boolean[] res = new boolean[queries.length];

        if (nums.length == 1) {
            Arrays.fill(res, true);
            return res;
        }

        int[] counterArr = new int[n];
        int counter = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                counter++;
            }
            counterArr[i] = counter;
        }

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0], end = queries[i][1];
            if (counterArr[start] != counterArr[end]) {
                res[i] = false;
            } else {
                res[i] = true;
            }
        }
        return res;
    }

    // 3153. Sum of Digit Differences of All Pairs
    public long sumDigitDifferences(int[] nums) {
        long res = 0L;
        String numberStr = String.valueOf(nums[0]);
        int n = nums.length;
        for (int j = numberStr.length() - 1; j >= 0; j--) {
            long subRes = 0L;
            int division = (int) Math.pow(10, j);
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int digit = nums[i] / division;
                nums[i] = nums[i] % division;
                map.put(digit, map.getOrDefault(digit, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                subRes += (long) (n - entry.getValue()) * entry.getValue();
            }
            res += subRes / 2;
        }
        return res;
    }

    // 3154. Find Number of Ways to Reach the K-th Stair
    public int waysToReachStair(int k) {
        int res = 0, d = 0, j = 0;
        int end = k > 0 ? (int) (Math.log(k) / Math.log(2)) : 0;

        for (j = 0; j < end + 3; j++) {
            int jump = (int) Math.pow(2, j);
            d = jump - k;
            if (d < 0 || d > j + 1) {
                continue;
            } else {
                // j + 1 is possible position for d
                res += comb(j + 1, k);
            }
        }
        return res;
    }

    private int comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        long res = 1;
        for (int i = 0; i < k; ++i)
            res = res * (n - i) / (i + 1);
        return (int)res;
    }
}
