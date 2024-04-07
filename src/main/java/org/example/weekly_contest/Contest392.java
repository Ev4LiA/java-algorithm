package org.example.weekly_contest;

import java.util.Arrays;

public class Contest392 {
    // 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
    public int longestMonotonicSubarray(int[] nums) {
        int down = 1, up = 1;
        int startDown = 0, startUp = 0, endDown = 0, endUp = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                endUp = i;
            } else {
                up = Math.max(up, endUp - startUp + 1);
                startUp = i;
            }

            if (nums[i] < nums[i - 1]) {
                endDown = i;
            } else {
                down = Math.max(down, endDown - startDown + 1);
                startDown = i;
            }

            if (i == nums.length - 1) {
                up = Math.max(up, endUp - startUp + 1);
                down = Math.max(down, endDown - startDown + 1);
            }
        }
        return Math.max(down, up);
    }

    // 3106. Lexicographically Smallest String After Operations With Constraint
    public String getSmallestString(String s, int k) {
        if (k == 0) return s;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length && k > 0; i++) {
            char c = arr[i];
            int distance = Math.min(c - 'a', Math.abs(c - 'a' - 26));

            if (distance <= k) {
                k -= distance;
                arr[i] = 'a';
            } else {
                if (c - k >= 97) {
                    arr[i] = (char) (c - k);
                } else {
                    arr[i] = (char) (c + k);
                }
                k = 0;
            }

            if (k == 0) {
                break;
            }
        }

        return new String(arr);
    }

    // 3107. Minimum Operations to Make Median of Array Equal to K
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        long res = 0;
        int pos = nums.length / 2;
        int median = nums[pos];
        if (median < k) {
            res += k - median;
            int start = pos + 1;
            while (start < nums.length && nums[start] < k) {
                res += k - nums[start];
                start++;
            }
        } else if (median > k) {
            res += median - k;
            int start = pos - 1;
            while (start >= 0 && nums[start] > k) {
                res += nums[start] - k;
                start--;
            }
        }
        return res;
    }

    // 3108. Minimum Cost Walk in Weighted Graph
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        return null;
    }
}
