package org.example.daily_challenge;

import java.util.Arrays;
import java.util.PriorityQueue;

public class August2024 {
    final int MOD = 1000000007;
    // 1460. Make Two Arrays Equal by Reversing Subarrays
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(arr);
        Arrays.sort(target);
        if (arr.length != target.length) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != target[i]) {
                return false;
            }
        }
        return true;
    }

    // 1508. Range Sum of Sorted Subarray Sums
    public int rangeSum(int[] nums, int n, int left, int right) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < nums.length; i++) {
            pq.add(new int[]{nums[i], i + 1});
        }

        int res = 0;

        for (int i = 1; i <= right; i++) {
            int[] arr = pq.poll();
            int val = arr[0], index = arr[1];
            if (i >= left) res = (res + val) % MOD;
            if (index < n) {
                val += nums[index];
                pq.add(new int[]{val, index + 1});
            }
        }
        return res;
    }

    // 2134. Minimum Swaps to Group All 1's Together II
    public int minSwaps(int[] nums) {
        int n = nums.length, ones = 0, max = 0, count = 0;
        for (int num : nums) {
            ones += num;
        }

        for (int i = 0; i < ones; i++) {
            count+= nums[i];
        }

        max = count;
        for  (int i = ones; i < n + ones; i++) {
            count = count - nums[i - ones] + nums[i % n];
            max = Math.max(max, count);
        }
        return ones - max;
    }

    // 2678. Number of Senior Citizens
    public int countSeniors(String[] details) {
        int res = 0;
        for (String detail : details) {
            int age = Integer.parseInt(detail.substring(11, 13));
            if (age > 60) res++;
        }
        return res;
    }
}
