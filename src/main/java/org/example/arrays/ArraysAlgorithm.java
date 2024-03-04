package org.example.arrays;

import java.util.Arrays;

public class ArraysAlgorithm {
    // 42. Trapping Rain Water
    public int trap(int[] height) {
        int left = 0, right = height.length - 1, lMax = Integer.MIN_VALUE, rMax = Integer.MIN_VALUE, trap = 0;
        while (left < right) {
            lMax = Math.max(height[left], lMax);
            rMax = Math.max(height[right], rMax);

            trap += (lMax < rMax) ? lMax - height[left++] : rMax - height[right--];
        }
        return trap;
    }

    // 134. Gas Station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int n = gas.length;
        int tank = 0, total = 0;
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return total < 0 ? -1 : start;
    }

    // 135. Candy
    public int candy(int[] ratings) {
        int res = 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        res = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            }
            res += candies[i];
        }
        return res;
    }

    // 189 Rotate Array
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // 238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int left = 1, right = 1;
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);
        for (int i = 0; i < nums.length; i++) {
            res[i] = res[i] * left;
            left = left * nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }
        return res;
    }

    // 274. H-Index
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int cite : citations) {
            if (cite >= n) {
                bucket[n]++;
            } else {
                bucket[cite]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[n];
            if (count >= i) return i;
        }
        return 0;
    }

}
