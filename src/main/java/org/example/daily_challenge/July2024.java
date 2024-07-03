package org.example.daily_challenge;

import java.util.Arrays;

public class July2024 {
    // 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
    public int minDifference(int[] nums) {
        Arrays.sort(nums);
        int max = Integer.MAX_VALUE, n = nums.length;
        if (n <= 3) return 0;
        max = Math.min(max, nums[n - 1] - nums[3]);
        max = Math.min(max, nums[n - 2] - nums[2]);
        max = Math.min(max, nums[n - 3] - nums[1]);
        max = Math.min(max, nums[n - 4] - nums[0]);
        return max;
    }

    // 1550. Three Consecutive Odds
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 == 1) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) return true;
        }
        return false;
    }
}
