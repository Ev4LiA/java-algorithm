package org.example.weekly_contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Contest388 {
    // 3074. Apple Redistribution into Boxes
    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int sum = 0;
        for (int a : apple) {
            sum += a;
        }
        int res = 0;
        for (int i = capacity.length - 1; i >= 0; i--) {
            if (sum > 0) {
                sum -= capacity[i];
                res++;
            } else {
                break;
            }
        }
        return res;
    }

    // 3075. Maximize Happiness of Selected Children
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int i = happiness.length - 1, j = 0;
        long res = 0;
        while (k > 0) {
            if (happiness[i] - j >= 0) {
                res += happiness[i--] - j++;
            }
            k--;
        }
        return res;
    }

    // 3076. Shortest Uncommon Substring in an Array
    public String[] shortestSubstrings(String[] arr) {
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = helper(arr[i], arr, i);
        }
        return res;
    }

    public String helper(String str, String[] arr, int index) {
        int len = 1;
        List<String> res = new ArrayList<>();
        while (len <= str.length()) {
            for (int i = 0; i + len <= str.length(); i++) {
                boolean flag = false;
                String sb = str.substring(i, i + len);
                for (int j = 0; j < arr.length; j++) {
                    if (j == index) continue;
                    if (arr[j].contains(sb)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) res.add(sb);
            }
            if (res.isEmpty()) {
                len++;
            } else {
                Collections.sort(res);
                return res.get(0);
            }
        }
        return "";
    }


    // 3077. Maximum Strength of K Disjoint Subarrays
    Long[][][] dp;
    long min = Long.MIN_VALUE;

    public long maximumStrength(int[] nums, int k) {
        dp = new Long[2][k + 1][nums.length];
        return helper(nums, 0, k, 0);
    }

    private long helper(int[] nums, int idx, int k, int started) {
        if (k == 0) {
            return 0;
        } else if (idx == nums.length) {
            return k - started == 0 ? 0 : min;
        } else if (dp[started][k][idx] != null)
            return dp[started][k][idx];

        int mul = k % 2 == 1 ? 1 : -1;
        long result;

        long result2;
        long val;

        if (started == 1) {
            result = helper(nums, idx, k - 1, 0);

            val = k * mul;
            val *= nums[idx];

            result2 = val + helper(nums, idx + 1, k, 1);

            result = Math.max(result, result2);
        } else {
            result = helper(nums, idx + 1, k, 0);

            val = k * mul;
            val *= nums[idx];

            result2 = val + helper(nums, idx + 1, k, 1);

            result = Math.max(result, result2);
        }

        dp[started][k][idx] = result;
        return result;
    }
}
